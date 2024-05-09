package ksm.coroutines

import kotlinx.coroutines.CoroutineScope
import ksm.context.StateContext
import ksm.coroutines.interceptor.AwaitInterceptor
import ksm.coroutines.plugin.AwaitPlugin
import ksm.coroutines.plugin.CoroutinesPlugin
import ksm.plugin.plugin

public val StateContext.scope: CoroutineScope get() {
    return plugin(CoroutinesPlugin).coroutineScope(context = this)
}

public fun StateContext.addAwaitInterceptor(interceptor: AwaitInterceptor) {
    plugin(AwaitPlugin).addAwaitInterceptor(
        context = this,
        interceptor = interceptor
    )
}

public suspend fun StateContext.await() {
    plugin(AwaitPlugin).await(context = this)
}
