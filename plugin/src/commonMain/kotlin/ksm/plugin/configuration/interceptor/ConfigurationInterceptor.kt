package ksm.plugin.configuration.interceptor

import ksm.annotation.MutateContext
import ksm.context.StateContext

public fun interface ConfigurationInterceptor {
    @MutateContext
    public fun onConfigure(context: StateContext): StateContext
}

public operator fun ConfigurationInterceptor?.plus(other: ConfigurationInterceptor): ConfigurationInterceptor {
    return CombinedConfigurationInterceptor(
        first = this,
        second = other
    )
}
