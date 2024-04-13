package ksm.state

import ksm.StateController
import ksm.context.StateContext
import ksm.context.createChildContext
import ksm.state.data.setStateData
import ksm.state.name.setStateName
import ksm.typed.TypedValue

public inline fun <reified T> StateController.launch(
    name: String,
    data: T
) {
    launch(name, TypedValue.of(data))
}

public fun StateController.launch(
    name: String,
    data: TypedValue<*> = TypedValue.of(Unit)
) {
    context.launchChildContext(name, data)
}

public inline fun <reified T> StateContext.launchChildContext(
    name: String,
    data: T
) {
    launchChildContext(name, TypedValue.of(data))
}

public inline fun StateContext.launchChildContext(
    name: String,
    data: TypedValue<*> = TypedValue.of(Unit),
    setup: (StateContext) -> Unit = {}
) {
    createChildContext { child ->
        child.setStateName(name)
        child.setStateData(data)
        setup(child)
    }
}

