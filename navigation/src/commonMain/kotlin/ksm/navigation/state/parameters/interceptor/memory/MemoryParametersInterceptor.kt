package ksm.navigation.state.parameters.interceptor.memory

import ksm.navigation.state.parameters.interceptor.ParametersInterceptor
import ksm.typed.TypedValue

internal class MemoryParametersInterceptor : ParametersInterceptor {
    private val map = mutableMapOf<String, TypedValue.Generic>()

    override fun onPut(key: String, value: TypedValue) {
        map[key] = TypedValue.Generic.of(value)
    }

    override fun onReceive(key: String): TypedValue.Generic? {
        return map[key]
    }
}
