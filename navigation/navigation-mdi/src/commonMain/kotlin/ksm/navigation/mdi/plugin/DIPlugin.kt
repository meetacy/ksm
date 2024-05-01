package ksm.navigation.mdi.plugin

import app.meetacy.di.DI
import app.meetacy.di.builder.DIBuilder
import app.meetacy.di.builder.di
import ksm.annotation.MutateContext
import ksm.asStateController
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.Plugin
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor

public class DIPlugin(
    private val root: DI = di { },
    private val checkDependencies: Boolean = true,
    private val perStateDI: DIBuilder.() -> Unit = {}
) : Plugin {
    override val key: Companion = DIPlugin

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration())
        return context + DIEntry(root)
    }

    private inner class Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            context.addLifecycleInterceptor(Lifecycle())
            return context + DIEntry()
        }
    }

    private inner class Lifecycle : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            val entry = context.require(DIEntry)
            var di = di(root, checkDependencies, perStateDI) + di {
                val stateController by constant(context.asStateController())
            }
            val entryDI = entry.di
            if (entryDI != null) di += entryDI
            entry.di = di
        }
    }

    public fun di(context: StateContext): DI {
        return context.require(DIEntry).di ?: error("DI is not initialized")
    }

    public fun setDI(context: StateContext, di: DI) {
        context.require(DIEntry).di = di
    }

    public companion object : StateContext.Key<DIPlugin>
}
