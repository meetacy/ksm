package ksm.coroutines.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.coroutines.interceptor.AwaitInterceptor
import ksm.plugin.Plugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor

public object AwaitPlugin : Plugin.Singleton<AwaitPlugin> {

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            return context + AwaitEntry()
        }
    }

    public suspend fun await(context: StateContext) {
        context.require(AwaitEntry).await()
    }

    public fun addAwaitInterceptor(
        context: StateContext,
        interceptor: AwaitInterceptor
    ) {
        context.require(AwaitEntry).addAwaitInterceptor(interceptor)
    }
}
