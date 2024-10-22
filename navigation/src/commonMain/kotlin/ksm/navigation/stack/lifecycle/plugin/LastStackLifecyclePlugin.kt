package ksm.navigation.stack.lifecycle.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.navigation.stack.previousContextOrNull
import ksm.plugin.Plugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.lifecycle.addLifecycleInterceptor
import ksm.plugin.lifecycle.interceptor.LifecycleInterceptor
import ksm.plugin.lifecycle.plugin.LifecyclePlugin

// This plugin is installed after any of navigation plugins
public object LastStackLifecyclePlugin : Plugin.Singleton<FirstStackLifecyclePlugin> {
    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            context.addLifecycleInterceptor(Lifecycle)
            return context
        }
    }

    private object Lifecycle : LifecycleInterceptor {
        override fun onFinish(context: StateContext) {
            // Called after any plugin that should know about stack, because
            //  LastStackLifecyclePlugin is installed last before core plugins
            //  which don't need to know about stack
            context.previousContextOrNull
                ?.get(LifecyclePlugin)
                ?.onResume(context)
        }
    }
}
