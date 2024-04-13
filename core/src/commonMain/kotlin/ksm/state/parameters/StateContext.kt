package ksm.state.parameters

import ksm.context.StateContext
import ksm.plugin.plugin
import ksm.state.parameters.plugin.StateParametersPlugin
import ksm.typed.TypedValue
import kotlin.reflect.KType
import kotlin.reflect.typeOf

public fun <T> StateContext.setStateParameter(
    key: String,
    value: TypedValue<T>
) {
    plugin(StateParametersPlugin).put(
        context = this,
        key = key,
        value = value
    )
}

public inline fun <reified T> StateContext.receiveStateParameter(key: String): T? {
    return receiveStateParameter(key, typeOf<T>())
}

public fun <T> StateContext.receiveStateParameter(
    key: String,
    type: KType
): T? {
    return plugin(StateParametersPlugin).receive<T>(
        context = this,
        key = key
    )?.get(type)
}
