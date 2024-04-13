package ksm.context.configuration.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.Plugin

public object ConfigurationPlugin : Plugin.Singleton<ConfigurationPlugin> {

    @MutateContext
    override fun install(context: StateContext): StateContext {
        return context + ConfigurationStateController()
    }

    public fun addConfigurationInterceptor(
        context: StateContext,
        interceptor: ConfigurationInterceptor
    ) {
        context.require(ConfigurationStateController).addInterceptor(interceptor)
    }

    @MutateContext
    public fun onConfigure(context: StateContext): StateContext {
        return context.require(ConfigurationStateController).onConfigure(context)
    }
}
