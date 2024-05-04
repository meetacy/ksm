package ksm.navigation.compose.wrapper

import ksm.StateController
import ksm.asStateController
import ksm.navigation.compose.interceptor.ComposeInterceptor
import ksm.navigation.compose.plugin.ComposeContent

public fun interface ComposeWrapper {
    public fun StateController.wrap(content: ComposeContent): ComposeContent
}

public fun ComposeWrapper.toInterceptor(): ComposeInterceptor {
    return ComposeInterceptor { context, base ->
        val controller = context.asStateController()
        controller.wrap(base)
    }
}
