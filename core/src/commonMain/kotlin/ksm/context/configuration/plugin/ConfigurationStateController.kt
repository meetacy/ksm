package ksm.context.configuration.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor

internal class ConfigurationStateController : StateContext.Element {
    override val key = ConfigurationStateController

    private val interceptors = mutableListOf<ConfigurationInterceptor>()

    fun addInterceptor(interceptor: ConfigurationInterceptor) {
        interceptors += interceptor
    }

    @MutateContext
    fun onConfigure(context: StateContext): StateContext {
        return interceptors.fold(context) { acc, interceptor -> interceptor.onConfigure(acc) }
    }

    companion object : StateContext.Key<ConfigurationStateController>
}
