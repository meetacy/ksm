package ksm.result.plugin

import ksm.context.StateContext
import ksm.result.ResultHandler

internal class ResultEntry : StateContext.Element {
    override val key = ResultEntry

    private val unhandledResults = mutableMapOf<ResultId, Any?>()
    private val resultHandlers = mutableMapOf<ResultId, ResultHandler<*>>()

    fun setResult(id: ResultId, data: Any?) {
        unhandledResults[id] = data
    }

    fun registerLauncher(handler: ResultHandler<*>): ResultId {
        val id = ResultId(resultHandlers.size)
        resultHandlers[id] = handler
        handleResult(id)
        return id
    }

    fun onResume() {
        for (resultId in unhandledResults.keys.toList()) {
            handleResult(resultId)
        }
    }

    private fun handleResult(resultId: ResultId) {
        if (resultId !in unhandledResults) return
        val result = unhandledResults.getValue(resultId)
        resultHandlers[resultId]?.handleUnchecked(result)
        unhandledResults -= resultId
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> ResultHandler<T>.handleUnchecked(result: Any?) {
        handle(result as T)
    }

    companion object : StateContext.Key<ResultEntry>
}
