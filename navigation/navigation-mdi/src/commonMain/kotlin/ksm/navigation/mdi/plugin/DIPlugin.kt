package ksm.navigation.mdi.plugin

import app.meetacy.di.DI
import app.meetacy.di.builder.DIBuilder
import app.meetacy.di.builder.di
import app.meetacy.di.dependency.Dependencies
import app.meetacy.di.dependency.DependencyKey
import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.navigation.NavigationController
import ksm.plugin.Plugin
import ksm.navigation.mdi.addDIInterceptor
import ksm.navigation.mdi.interceptor.DIInterceptor
import ksm.navigation.mdi.interceptor.plus
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.factory.asController
import ksm.plugin.factory.controllerFactory
import ksm.plugin.lifecycle.addLifecycleInterceptor
import ksm.plugin.lifecycle.interceptor.LifecycleInterceptor

public class DIPlugin(
    private val root: DI = di { },
    private val checkDependencies: Boolean = true,
    private val perStateDI: DIBuilder.() -> Unit = {}
) : Plugin {
    override val key: Companion = DIPlugin

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration())
        val entry = DIEntry()
        entry.di = root
        return context + entry
    }

    private inner class Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            val applied = context + DIEntry()
            applied.addLifecycleInterceptor(Lifecycle())
            applied.addDIInterceptor(DefaultInterceptor())
            return applied
        }
    }

    private inner class Lifecycle : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            val entry = context.require(DIEntry)
            val di = entry.di ?: DI(Dependencies.Empty)
            val interceptor = entry.interceptor
            entry.di = interceptor?.intercept(context, di) ?: di
        }
    }

    private inner class DefaultInterceptor : DIInterceptor {
        override fun intercept(context: StateContext, base: DI): DI {
            var applied = base
            applied += di(root, checkDependencies, perStateDI)
            applied += di {
                val key = DependencyKey<NavigationController>(
                    type = context.controllerFactory.type,
                    name = "stateController"
                )
                register(key) { context.asController() }
            }
            return applied
        }
    }

    public fun addDIInterceptor(context: StateContext, interceptor: DIInterceptor) {
        context.require(DIEntry).interceptor += interceptor
    }

    public fun di(context: StateContext): DI {
        return context.require(DIEntry).di ?: error("DI is not initialized")
    }

    public fun setDI(context: StateContext, di: DI) {
        context.require(DIEntry).di = di
    }

    public companion object : StateContext.Key<DIPlugin>
}
