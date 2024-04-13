package ksm.coroutines

import kotlinx.coroutines.CoroutineScope
import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.coroutines.interceptor.AwaitInterceptor
import ksm.coroutines.plugin.AwaitPlugin
import ksm.coroutines.plugin.CoroutinesPlugin
import ksm.plugin.plugin
import kotlin.coroutines.CoroutineContext

public val StateContext.scope: CoroutineScope get() {
    return CoroutineScope(coroutineContext)
}

public val StateContext.coroutineContext: CoroutineContext get() {
    return plugin(CoroutinesPlugin).coroutineContext(context = this)
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
