package ksm.state.parameters.interceptor

import ksm.typed.TypedValue

public interface StateParametersInterceptor {
    public fun onPut(key: String, value: TypedValue<*>) {}
    public fun <T> onReceive(key: String): TypedValue.Generic<T>? = null
}
