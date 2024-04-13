package ksm.mdi.plugin

import app.meetacy.di.DI
import app.meetacy.di.builder.di
import ksm.StateController
import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.Plugin
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor

public class DIPlugin(private val root: DI) : Plugin {
    override val key: Companion = DIPlugin

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration())
        return context + DIEntry(root)
    }

    private inner class Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            val entry = DIEntry(root)
            context.addLifecycleInterceptor(Lifecycle(entry))
            return context + entry
        }
    }

    private inner class Lifecycle(val entry: DIEntry) : LifecycleInterceptor {
        override fun onFinish(context: StateContext) {
            entry.setDI(null)
        }
    }

    public fun di(context: StateContext): DI {
        val base = context.require(DIEntry).getDI() ?: error("DI is not initialized")
        return base + di {
            val stateController by constant(StateController(context))
        }
    }

    public fun setDI(context: StateContext, di: DI) {
        context.require(DIEntry).setDI(di)
    }

    public companion object : StateContext.Key<DIPlugin>
}
