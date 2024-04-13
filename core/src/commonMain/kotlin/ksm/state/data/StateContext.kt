package ksm.state.data

import ksm.context.StateContext
import ksm.state.parameters.receiveStateParameter
import ksm.state.parameters.setStateParameter
import ksm.typed.TypedValue
import kotlin.reflect.KType

public const val STATE_DATA_KEY: String = "ksm.state.data.STATE_DATA"

public inline fun <reified T> StateContext.setStateData(value: T) {
    setStateData(TypedValue.of(value))
}

public fun StateContext.setStateData(value: TypedValue<*>) {
    setStateParameter(STATE_DATA_KEY, value)
}

public inline fun <reified T> StateContext.receive(): T {
    return receiveStateParameter<T>(STATE_DATA_KEY) ?: error("Data is not provided")
}

public fun StateContext.receive(type: KType): Any? {
    return receiveStateParameter(STATE_DATA_KEY, type)
}
