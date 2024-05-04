package ksm.configuration.interceptor

import ksm.annotation.MutateContext
import ksm.context.StateContext

internal class CombinedConfigurationInterceptor(
    private val first: ConfigurationInterceptor?,
    private val second: ConfigurationInterceptor
) : ConfigurationInterceptor {
    @MutateContext
    override fun onConfigure(context: StateContext): StateContext {
        var applied = context
        applied = first?.onConfigure(applied) ?: applied
        applied = second.onConfigure(applied)
        return applied
    }
}
