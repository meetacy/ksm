package ksm.coroutines.plugin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.newCoroutineContext
import ksm.context.StateContext
import ksm.coroutines.interceptor.AwaitInterceptor
import kotlin.coroutines.CoroutineContext

internal class CoroutinesEntry(
    context: StateContext,
    initialCoroutineContext: CoroutineContext
) : StateContext.Element {
    override val key = CoroutinesEntry

    val coroutineContext: CoroutineContext

    init {
        val parentCoroutineContext = context[CoroutinesEntry]
            ?.coroutineContext
            ?: initialCoroutineContext

        val parentJob = parentCoroutineContext[Job]
        val job = Job(parentJob)

        coroutineContext = CoroutineScope(parentCoroutineContext).newCoroutineContext(job)
    }

    fun cancel() = coroutineContext.cancel()

    companion object : StateContext.Key<CoroutinesEntry>
}
