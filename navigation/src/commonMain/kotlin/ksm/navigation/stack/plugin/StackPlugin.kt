package ksm.navigation.stack.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.navigation.NavigationController
import ksm.plugin.Plugin
import ksm.navigation.annotation.InstallStackPlugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.lifecycle.addLifecycleInterceptor
import ksm.plugin.lifecycle.interceptor.LifecycleInterceptor
import ksm.plugin.lifecycle.plugin.LifecyclePlugin

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
