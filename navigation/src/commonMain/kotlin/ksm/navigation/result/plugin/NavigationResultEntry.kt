package ksm.navigation.result.plugin

import ksm.context.StateContext
import ksm.navigation.result.NavigationResultHandler

internal class NavigationResultEntry : StateContext.Element {
    override val key = NavigationResultEntry

    private val unhandledResults = mutableMapOf<NavigationResultId, Any?>()
    private val resultHandlers = mutableMapOf<NavigationResultId, NavigationResultHandler<*>>()

    fun setResult(id: NavigationResultId, data: Any?) {
        unhandledResults[id] = data
    }

    fun registerNavigator(handler: NavigationResultHandler<*>): NavigationResultId {
        val id = NavigationResultId(resultHandlers.size)
        resultHandlers[id] = handler
        handleResult(id)
        return id
    }

    fun onResume() {
        for (resultId in unhandledResults.keys.toList()) {
            handleResult(resultId)
        }
    }

    private fun handleResult(resultId: NavigationResultId) {
        if (resultId !in unhandledResults) return
        val result = unhandledResults.getValue(resultId)
        resultHandlers[resultId]?.handleUnchecked(result)
        unhandledResults -= resultId
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> NavigationResultHandler<T>.handleUnchecked(result: Any?) {
        handle(result as T)
    }

    companion object : StateContext.Key<NavigationResultEntry>
}
