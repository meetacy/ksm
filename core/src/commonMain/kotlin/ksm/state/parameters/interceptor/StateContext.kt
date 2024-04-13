package ksm.state.parameters.interceptor

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.plugin.plugin
import ksm.state.parameters.plugin.StateParametersPlugin
import ksm.typed.TypedValue

@MutateContext
public fun StateContext.addParametersInterceptor(
    onPut: (key: String, value: TypedValue<*>) -> Unit = { _, _ -> },
    onReceive: (String) -> TypedValue.Generic<*>? = { null }
) {
    val interceptor = object : StateParametersInterceptor {
        override fun onPut(key: String, value: TypedValue<*>) {
            onPut(key, value)
        }
        override fun <T> onReceive(key: String): TypedValue.Generic<T>? {
            @Suppress("UNCHECKED_CAST")
            return onReceive(key) as TypedValue.Generic<T>?
        }
    }
    addParametersInterceptor(interceptor)
}

@MutateContext
public fun StateContext.addParametersInterceptor(
    interceptor: StateParametersInterceptor
) {
    plugin(StateParametersPlugin).addParametersInterceptor(
        context = this,
        interceptor = interceptor
    )
}
