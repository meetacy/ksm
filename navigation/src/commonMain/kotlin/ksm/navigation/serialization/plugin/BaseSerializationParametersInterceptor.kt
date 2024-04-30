package ksm.navigation.serialization.plugin

import ksm.navigation.state.parameters.interceptor.ParametersInterceptor
import ksm.typed.TypedValue

internal class BaseSerializationParametersInterceptor : ParametersInterceptor {
    private val map = mutableMapOf<String, TypedValue.Generic>()

    override fun onPut(
        key: String,
        value: TypedValue
    ) { map[key] = TypedValue.Generic.of(value) }
    override fun onReceive(key: String): TypedValue.Generic? = map[key]
    fun toMap(): Map<String, TypedValue.Generic> = map
}
