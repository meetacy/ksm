package ksm.navigation.compose.interceptor

import ksm.context.StateContext
import ksm.navigation.compose.plugin.ComposeContent

internal class CombinedComposeInterceptor(
    private val first: ComposeInterceptor?,
    private val second: ComposeInterceptor
) : ComposeInterceptor {
    override fun intercept(context: StateContext, base: ComposeContent): ComposeContent {
        var applied = base
        applied = first?.intercept(context, applied) ?: applied
        applied = second.intercept(context, applied)
        return applied
    }
}
