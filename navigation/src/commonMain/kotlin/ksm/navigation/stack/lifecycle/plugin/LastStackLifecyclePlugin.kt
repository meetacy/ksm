package ksm.navigation.stack.lifecycle.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.configuration.interceptor.ConfigurationInterceptor
import ksm.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.interceptor.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
import ksm.lifecycle.plugin.LifecyclePlugin
import ksm.navigation.stack.previousContextOrNull
import ksm.plugin.Plugin

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
