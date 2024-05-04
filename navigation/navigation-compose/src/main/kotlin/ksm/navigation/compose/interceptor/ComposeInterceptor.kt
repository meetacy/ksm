package ksm.navigation.compose.interceptor

import ksm.context.StateContext
import ksm.navigation.compose.plugin.ComposeContent

public fun interface ComposeInterceptor {
    public fun intercept(
        context: StateContext,
        base: ComposeContent
    ): ComposeContent
}

public operator fun ComposeInterceptor?.plus(other: ComposeInterceptor): ComposeInterceptor {
    return CombinedComposeInterceptor(
        first = this,
        second = other
    )
}
