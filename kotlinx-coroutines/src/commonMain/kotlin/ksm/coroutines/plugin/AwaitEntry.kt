package ksm.coroutines.plugin

import ksm.context.StateContext
import ksm.coroutines.interceptor.AwaitInterceptor

internal class AwaitEntry : StateContext.Element {
    override val key = AwaitEntry

    private val awaitInterceptors = mutableListOf<AwaitInterceptor>()

    suspend fun await() {
        for (interceptor in awaitInterceptors) {
            interceptor.await()
        }
    }

    fun addAwaitInterceptor(interceptor: AwaitInterceptor) {
        awaitInterceptors += interceptor
    }

    companion object : StateContext.Key<AwaitEntry>
}
