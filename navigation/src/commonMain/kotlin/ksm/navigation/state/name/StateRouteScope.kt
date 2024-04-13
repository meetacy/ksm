package ksm.navigation.state.name

import ksm.navigation.state.builder.StateBuilderScope
import ksm.navigation.state.builder.StateRouteScope

public inline fun StateRouteScope.named(
    string: String,
    block: StateBuilderScope.() -> Unit
) {
    intercept(
        predicate = { stateRouteContextForFilter.stateNameOrNull == string },
        block = block
    )
}
