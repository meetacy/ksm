package ksm.finish.once.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.configuration.interceptor.ConfigurationInterceptor
import ksm.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.Plugin

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
