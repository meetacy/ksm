package ksm.configuration.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.configuration.interceptor.ConfigurationInterceptor
import ksm.configuration.interceptor.plus
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
        context.require(ConfigurationStateController).interceptor += interceptor
    }

    @MutateContext
    public fun onConfigure(context: StateContext): StateContext {
        return context.require(ConfigurationStateController)
            .interceptor?.onConfigure(context)
            ?: context
    }
}
