package ksm.coroutines.plugin

import kotlinx.coroutines.*
import ksm.context.StateContext
import kotlin.coroutines.CoroutineContext

internal class CoroutinesEntry(
    context: StateContext,
    initialCoroutineScope: CoroutineScope
) : StateContext.Element {
    override val key = CoroutinesEntry

    val coroutineScope: CoroutineScope

    init {
        val parentCoroutineScope = context[CoroutinesEntry]
            ?.coroutineScope
            ?: initialCoroutineScope

        val parentJob = parentCoroutineScope.coroutineContext[Job]
        val job = Job(parentJob)

        coroutineScope = parentCoroutineScope + job
    }

    fun cancel() = coroutineScope.cancel()

    companion object : StateContext.Key<CoroutinesEntry>
}
