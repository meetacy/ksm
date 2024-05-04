package ksm.navigation.compose

import ksm.context.StateContext
import ksm.navigation.compose.interceptor.ComposeInterceptor
import ksm.navigation.compose.plugin.ComposePlugin
import ksm.plugin.plugin

public fun StateContext.addComposeInterceptor(interceptor: ComposeInterceptor) {
    plugin(ComposePlugin).addComposeInterceptor(
        context = this,
        interceptor = interceptor
    )
}
