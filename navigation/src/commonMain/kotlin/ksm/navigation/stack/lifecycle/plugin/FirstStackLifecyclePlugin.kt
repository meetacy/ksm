package ksm.navigation.stack.lifecycle.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.plugin.Plugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.lifecycle.plugin.LifecyclePlugin

// This plugin is installed before any of navigation plugins
public object FirstStackLifecyclePlugin : Plugin.Singleton<FirstStackLifecyclePlugin> {
    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            // Called before any plugin that should know about stack, because
            //  FirstStackLifecyclePlugin is installed first after core plugins
            //  which don't need to know about stack
            context[LifecyclePlugin]?.onPause(context)
            return context
        }
    }
}
