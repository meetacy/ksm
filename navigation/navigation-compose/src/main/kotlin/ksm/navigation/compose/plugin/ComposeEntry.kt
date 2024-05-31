package ksm.navigation.compose.plugin

import ksm.context.StateContext
import ksm.navigation.compose.interceptor.ComposeInterceptor

internal class ComposeEntry(var interceptor: ComposeInterceptor?) : StateContext.Element {
    override val key = ComposeEntry

    var content: ComposeContent? = null

    companion object : StateContext.Key<ComposeEntry>
}
