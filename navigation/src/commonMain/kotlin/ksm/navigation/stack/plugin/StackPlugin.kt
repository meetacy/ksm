package ksm.navigation.stack.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.Plugin
import ksm.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.interceptor.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
import ksm.lifecycle.plugin.LifecyclePlugin
import ksm.navigation.annotation.InstallStackPlugin

@InstallStackPlugin
public object StackPlugin : Plugin.Singleton<StackPlugin> {

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            val previousEntry = context[NavigationStackEntry]
            context[LifecyclePlugin]?.onPause(context)
            val entry = NavigationStackEntry(previousEntry)
            context.addLifecycleInterceptor(Lifecycle(entry))
            return context + entry
        }
    }

    private class Lifecycle(val entry: NavigationStackEntry) : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            entry.attachContext(context)
        }
        override fun onFinish(context: StateContext) {
            entry.onFinish()
        }
    }

    public fun previousContextOrNull(context: StateContext): StateContext? {
        return context.require(NavigationStackEntry).previousContext
    }

    public fun nextContextOrNull(
        context: StateContext
    ): StateContext? {
        return context.require(NavigationStackEntry).nextContext
    }
}
