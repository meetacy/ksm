package ksm.navigation.state.parameters

import ksm.context.StateContext
import ksm.navigation.state.parameters.plugin.StateParametersPlugin
import ksm.plugin.plugin
import ksm.typed.TypedValue
import kotlin.reflect.KType
import kotlin.reflect.typeOf

public inline fun <reified T> StateContext.setParameter(key: String, data: T) {
    return setParameter(key, TypedValue.of(data))
}

public fun <T> StateContext.setParameter(
    key: String,
    value: TypedValue<T>
) {
    plugin(StateParametersPlugin).put(
        context = this,
        key = key,
        value = value
    )
}

public inline fun <reified T> StateContext.receiveParameter(key: String): T? {
    return receiveParameter(key, typeOf<T>())
}

public fun <T> StateContext.receiveParameter(
    key: String,
    type: KType
): T? {
    return plugin(StateParametersPlugin).receive<T>(
        context = this,
        key = key
    )?.get(type)
}
