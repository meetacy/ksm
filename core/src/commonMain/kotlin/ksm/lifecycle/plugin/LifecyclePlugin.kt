package ksm.lifecycle.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.configuration.interceptor.ConfigurationInterceptor
import ksm.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.interceptor.LifecycleInterceptor
import ksm.lifecycle.interceptor.plus
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
        interceptor: LifecycleInterceptor
    ) {
        context.require(LifecycleEntry).interceptor += interceptor
    }

    public fun onCreate(context: StateContext) {
        context.require(LifecycleEntry).interceptor?.onCreate(context)
    }
    public fun onResume(context: StateContext) {
        context.require(LifecycleEntry).interceptor?.onResume(context)
    }
    public fun onPause(context: StateContext) {
        context.require(LifecycleEntry).interceptor?.onPause(context)
    }
    public fun onFinish(context: StateContext) {
        context.require(LifecycleEntry).interceptor?.onFinish(context)
    }
    public fun onChildCreate(
        context: StateContext,
        afterConfigure: StateContext
    ): StateContext {
        return context.require(LifecycleEntry)
            .interceptor?.onChildCreate(afterConfigure)
            ?: afterConfigure
    }
}
