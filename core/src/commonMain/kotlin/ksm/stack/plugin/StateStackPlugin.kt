package ksm.stack.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.Plugin
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor

public object StateStackPlugin : Plugin.Singleton<StateStackPlugin> {

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            val entry = StateStackEntry(context[StateStackEntry])
            context.addLifecycleInterceptor(Lifecycle(entry))
            return context + entry
        }
    }

    private class Lifecycle(val entry: StateStackEntry) : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            entry.attachContext(context)
        }
        override fun onFinish(context: StateContext) {
            entry.onFinish()
        }
    }

    public fun previousContextOrNull(context: StateContext): StateContext? {
        return context.require(StateStackEntry).previousContext
    }

    public fun nextContextOrNull(
        context: StateContext
    ): StateContext? {
        return context.require(StateStackEntry).nextContext
    }
}
