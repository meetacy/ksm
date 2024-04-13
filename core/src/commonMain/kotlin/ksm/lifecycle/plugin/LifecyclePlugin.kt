package ksm.lifecycle.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.LifecycleInterceptor
import ksm.plugin.Plugin

public object LifecyclePlugin : Plugin.Singleton<LifecyclePlugin> {

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context + LifecycleEntry()
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            return context + LifecycleEntry()
        }
    }

    public fun addLifecycleInterceptor(
        context: StateContext,
        observer: LifecycleInterceptor
    ) {
        context.require(LifecycleEntry).addInterceptor(observer)
    }

    public fun onCreate(context: StateContext) {
        context.require(LifecycleEntry).onCreate(context)
    }
    public fun onResume(context: StateContext) {
        context.require(LifecycleEntry).onResume(context)
    }
    public fun onPause(context: StateContext) {
        context.require(LifecycleEntry).onPause(context)
    }
    public fun onFinish(context: StateContext) {
        context.require(LifecycleEntry).onFinish(context)
    }
}
