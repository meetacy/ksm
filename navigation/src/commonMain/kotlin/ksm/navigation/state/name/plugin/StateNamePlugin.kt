package ksm.navigation.state.name.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.Plugin

public object StateNamePlugin : Plugin.Singleton<StateNamePlugin> {

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            return context + StateNameEntry()
        }
    }

    public fun setName(
        context: StateContext,
        name: String
    ) {
        context.require(StateNameEntry).name = name
    }

    public fun stateName(context: StateContext): String? {
        return context.require(StateNameEntry).name
    }
}
