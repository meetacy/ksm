package ksm.navigation.destination.parameters.plugin

import ksm.context.StateContext
import ksm.navigation.destination.parameters.interceptor.ParametersInterceptor
import ksm.typed.TypedValue

internal class DestinationParametersEntry : StateContext.Element {
    override val key = DestinationParametersEntry

    private val interceptors = mutableListOf<ParametersInterceptor>()

    fun addInterceptor(interceptor: ParametersInterceptor) {
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

    companion object : StateContext.Key<DestinationParametersEntry>
}
