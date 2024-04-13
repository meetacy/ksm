package ksm.state.name

import ksm.state.builder.StateBuilderScope
import ksm.state.builder.StateRouteScope

public inline fun StateRouteScope.named(
    string: String,
    block: StateBuilderScope.() -> Unit
) {
    intercept(
        predicate = { context.stateNameOrNull == string },
        block = block
    )
}
