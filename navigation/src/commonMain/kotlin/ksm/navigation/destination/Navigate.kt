package ksm.navigation.destination

import ksm.StateController
import ksm.context.StateContext
import ksm.context.createChildContext
import ksm.navigation.destination.data.setDestinationData
import ksm.navigation.destination.name.setName
import ksm.typed.TypedValue

public inline fun <reified T> StateController.navigate(
    name: String,
    data: T
) {
    navigate(name, TypedValue.of(data))
}

public fun StateController.navigate(
    name: String,
    data: TypedValue<*> = TypedValue.of(Unit)
) {
    context.navigate(name, data)
}

public inline fun <reified T> StateContext.navigate(
    name: String,
    data: T
) {
    navigate(name, TypedValue.of(data))
}

public inline fun StateContext.navigate(
    name: String,
    data: TypedValue<*> = TypedValue.of(Unit),
    setup: (StateContext) -> Unit = {}
) {
    createChildContext { child ->
        child.setName(name)
        child.setDestinationData(data)
        setup(child)
    }
}
