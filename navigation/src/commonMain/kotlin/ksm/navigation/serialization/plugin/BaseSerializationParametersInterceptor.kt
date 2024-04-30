package ksm.navigation.serialization.plugin

import ksm.navigation.state.parameters.interceptor.ParametersInterceptor
import ksm.typed.TypedValue

internal class BaseSerializationParametersInterceptor : ParametersInterceptor {
    private val map = mutableMapOf<String, TypedValue>()

    override fun onPut(
        key: String,
        value: TypedValue
    ) { map[key] = value }

    override fun onReceive(key: String): TypedValue.Generic? {
        return map[key]?.let { value -> TypedValue.Generic.of(value) }
    }

    fun toMap(): Map<String, TypedValue> = map
}
