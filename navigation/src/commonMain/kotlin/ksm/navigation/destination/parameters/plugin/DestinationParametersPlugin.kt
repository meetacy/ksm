package ksm.navigation.destination.parameters.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.navigation.destination.parameters.interceptor.ParametersInterceptor
import ksm.navigation.destination.parameters.interceptor.memory.MemoryParametersInterceptor
import ksm.plugin.Plugin
import ksm.typed.TypedValue

public object DestinationParametersPlugin : Plugin.Singleton<DestinationParametersPlugin> {

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
            val entry = DestinationParametersEntry()
            entry.addInterceptor(MemoryParametersInterceptor())
            return context + entry
        }
    }

    public fun <T> put(
        context: StateContext,
        key: String,
        value: TypedValue<T>
    ) {
        context.require(DestinationParametersEntry).onPut(key, value)
    }

    public fun <T> receive(
        context: StateContext,
        key: String
    ): TypedValue.Generic<T>? {
        return context.require(DestinationParametersEntry).onReceive(key)
    }

    public fun addParametersInterceptor(
        context: StateContext,
        interceptor: ParametersInterceptor
    ) {
        context.require(DestinationParametersEntry).addInterceptor(interceptor)
    }
}
