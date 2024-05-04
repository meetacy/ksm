package ksm.navigation.compose.interceptor

import ksm.navigation.compose.plugin.ComposeContent

internal class CombinedComposeInterceptor(
    private val first: ComposeInterceptor?,
    private val second: ComposeInterceptor
) : ComposeInterceptor {
    override fun intercept(base: ComposeContent): ComposeContent {
        var applied = base
        applied = first?.intercept(applied) ?: applied
        applied = second.intercept(applied)
        return applied
    }
}
