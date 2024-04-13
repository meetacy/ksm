package ksm.serialization.plugin

import ksm.state.parameters.interceptor.StateParametersInterceptor
import ksm.typed.TypedValue

internal class BaseSerializationParametersInterceptor : StateParametersInterceptor {
    private val map = mutableMapOf<String, TypedValue.Generic<*>>()

    override fun onPut(
        key: String,
        value: TypedValue<*>
    ) {
        map[key] = TypedValue.Generic.of(value)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> onReceive(key: String): TypedValue.Generic<T>? {
        return map[key] as TypedValue.Generic<T>?
    }

    fun toMap(): Map<String, TypedValue.Generic<*>> {
        return map
    }
}
