package ksm.context.configuration.interceptor

import ksm.annotation.MutateContext
import ksm.context.StateContext

public fun interface ConfigurationInterceptor {

    @MutateContext
    public fun onConfigure(context: StateContext): StateContext
}
