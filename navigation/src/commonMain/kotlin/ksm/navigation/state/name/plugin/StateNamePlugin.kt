package ksm.navigation.state.name.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.plugin.Plugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor

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
