package ksm.navigation.state.name

import ksm.navigation.state.route.StateBuilderScope
import ksm.navigation.state.route.StateRouteScope

public inline fun StateRouteScope.named(
    string: String,
    block: StateBuilderScope.() -> Unit
) {
    intercept(
        predicate = { stateRouteContextForFilter.stateNameOrNull == string },
        block = block
    )
}
