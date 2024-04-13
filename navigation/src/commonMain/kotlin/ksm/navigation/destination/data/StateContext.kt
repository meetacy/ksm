package ksm.navigation.destination.data

import ksm.context.StateContext
import ksm.navigation.destination.parameters.receiveParameter
import ksm.navigation.destination.parameters.setParameter
import ksm.typed.TypedValue
import kotlin.reflect.KType

public const val DESTINATION_DATA_KEY: String = "ksm.state.data.STATE_DATA"

public inline fun <reified T> StateContext.setDestinationData(value: T) {
    setDestinationData(TypedValue.of(value))
}

public fun StateContext.setDestinationData(value: TypedValue<*>) {
    setParameter(DESTINATION_DATA_KEY, value)
}

public inline fun <reified T> StateContext.receive(): T {
    return receiveParameter<T>(DESTINATION_DATA_KEY) ?: error("Data is not provided")
}

public fun StateContext.receive(type: KType): Any? {
    return receiveParameter(DESTINATION_DATA_KEY, type)
}
