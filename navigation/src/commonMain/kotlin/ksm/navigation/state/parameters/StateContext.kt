package ksm.navigation.state.parameters

import ksm.context.StateContext
import ksm.navigation.state.parameters.plugin.StateParametersPlugin
import ksm.plugin.plugin
import ksm.typed.TypedValue
import ksm.typed.get
import kotlin.reflect.KType
import kotlin.reflect.typeOf

public inline fun <reified T> StateContext.setParameter(key: String, data: T) {
    return setParameter(key, TypedValue.of(data))
}

public fun StateContext.setParameter(
    key: String,
    value: TypedValue
) {
    plugin(StateParametersPlugin).put(
        context = this,
        key = key,
        value = value
    )
}

public inline fun <reified T> StateContext.receiveParameter(key: String): T? {
    return plugin(StateParametersPlugin).receive(
        context = this,
        key = key
    )?.get<T>()
}

public fun StateContext.receiveParameter(
    key: String,
    type: KType
): Any? {
    return plugin(StateParametersPlugin).receive(
        context = this,
        key = key
    )?.get(type)
}
