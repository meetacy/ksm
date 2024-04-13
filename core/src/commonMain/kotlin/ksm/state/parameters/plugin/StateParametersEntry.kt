package ksm.state.parameters.plugin

import ksm.context.StateContext
import ksm.state.parameters.interceptor.StateParametersInterceptor
import ksm.typed.TypedValue

internal class StateParametersEntry : StateContext.Element {
    override val key = StateParametersEntry

    private val interceptors = mutableListOf<StateParametersInterceptor>()

    fun addInterceptor(interceptor: StateParametersInterceptor) {
        interceptors += interceptor
    }

    fun onPut(
        key: String,
        value: TypedValue<*>
    ) {
        for (interceptor in interceptors) {
            interceptor.onPut(key, value)
        }
    }

    fun <T> onReceive(key: String): TypedValue.Generic<T>? {
        for (interceptor in interceptors.asReversed()) {
            val value = interceptor.onReceive<T>(key)
            if (value != null) return value
        }
        return null
    }

    companion object : StateContext.Key<StateParametersEntry>
}
