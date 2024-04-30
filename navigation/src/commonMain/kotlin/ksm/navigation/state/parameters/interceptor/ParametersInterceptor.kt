package ksm.navigation.state.parameters.interceptor

import ksm.typed.TypedValue

public interface ParametersInterceptor {
    public fun onPut(key: String, value: TypedValue) {}
    public fun onReceive(key: String): TypedValue.Generic? = null
}
