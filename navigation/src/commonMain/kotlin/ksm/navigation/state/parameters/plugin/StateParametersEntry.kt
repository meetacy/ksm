package ksm.navigation.state.parameters.plugin

import ksm.context.StateContext
import ksm.navigation.state.parameters.interceptor.ParametersInterceptor
import ksm.typed.TypedValue

internal class StateParametersEntry : StateContext.Element {
    override val key = StateParametersEntry

    private val interceptors = mutableListOf<ParametersInterceptor>()

    fun addInterceptor(interceptor: ParametersInterceptor) {
        interceptors += interceptor
    }

    fun onPut(
        key: String,
        value: TypedValue
    ) {
        for (interceptor in interceptors) {
            interceptor.onPut(key, value)
        }
    }

    fun onReceive(key: String): TypedValue.Generic? {
        for (interceptor in interceptors.asReversed()) {
            val value = interceptor.onReceive(key)
            if (value != null) return value
        }
        return null
    }

    companion object : StateContext.Key<StateParametersEntry>
}
