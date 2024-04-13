package ksm.navigation.destination.name.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.Plugin

public object DestinationNamePlugin : Plugin.Singleton<DestinationNamePlugin> {

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            return context + DestinationNameEntry()
        }
    }

    public fun setName(
        context: StateContext,
        name: String
    ) {
        context.require(DestinationNameEntry).name = name
    }

    public fun stateName(context: StateContext): String? {
        return context.require(DestinationNameEntry).name
    }
}
