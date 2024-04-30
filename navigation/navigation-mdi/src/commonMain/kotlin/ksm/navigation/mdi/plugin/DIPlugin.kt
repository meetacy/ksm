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
        val di = di(
            di = root,
            checkDependencies = checkDependencies,
            block = perStateDI
        )
        return context + DIEntry(di)
    }

    private inner class Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            context.addLifecycleInterceptor(Lifecycle())
            val di = di(
                di = root,
                checkDependencies = checkDependencies,
                block = perStateDI
            )
            return context + DIEntry(di)
        }
    }

    private inner class Lifecycle : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            val entry = context.require(DIEntry)
            val controllerDI = di {
                val stateController by constant(context.asStateController())
            }
            entry.setDI(di = entry.getDI()?.plus(controllerDI))
        }
    }

    public fun di(context: StateContext): DI {
        return context.require(DIEntry).getDI() ?: error("DI is not initialized")
    }

    public fun setDI(context: StateContext, di: DI) {
        context.require(DIEntry).setDI(di)
    }

    public companion object : StateContext.Key<DIPlugin>
}
