package ksm.plugin.finish.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.plugin.Plugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor

public object FinishOncePlugin : Plugin.Singleton<FinishOncePlugin> {

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            return context + FinishOnceEntry()
        }
    }

    public fun checkCanCreate(context: StateContext) {
        context.require(FinishOnceEntry).checkCanCreate()
    }

    public fun finish(context: StateContext) {
        context.require(FinishOnceEntry).finish()
    }
}
