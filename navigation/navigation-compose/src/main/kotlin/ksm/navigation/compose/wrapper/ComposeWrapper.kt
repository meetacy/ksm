package ksm.navigation.compose.wrapper

import ksm.navigation.compose.interceptor.ComposeInterceptor
import ksm.navigation.compose.plugin.ComposeContent

public fun interface ComposeWrapper {
    public fun wrap(content: ComposeContent): ComposeContent
}

public fun ComposeWrapper.toInterceptor(): ComposeInterceptor {
    return ComposeInterceptor(::wrap)
}
