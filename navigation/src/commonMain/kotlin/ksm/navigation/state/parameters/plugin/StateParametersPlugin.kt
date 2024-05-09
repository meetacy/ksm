package ksm.navigation.state.parameters.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.navigation.state.parameters.interceptor.ParametersInterceptor
import ksm.navigation.state.parameters.interceptor.memory.MemoryParametersInterceptor
import ksm.plugin.Plugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor
import ksm.typed.TypedValue

public object StateParametersPlugin : Plugin.Singleton<StateParametersPlugin> {

    @MutateContext
    override fun install(
        context: StateContext
    ): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }
    
    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            val entry = StateParametersEntry()
            entry.addInterceptor(MemoryParametersInterceptor())
            return context + entry
        }
    }

    public fun put(
        context: StateContext,
        key: String,
        value: TypedValue
    ) {
        context.require(StateParametersEntry).onPut(key, value)
    }

    public fun receive(
        context: StateContext,
        key: String
    ): TypedValue.Generic? {
        return context.require(StateParametersEntry).onReceive(key)
    }

    public fun addParametersInterceptor(
        context: StateContext,
        interceptor: ParametersInterceptor
    ) {
        context.require(StateParametersEntry).addInterceptor(interceptor)
    }
}
