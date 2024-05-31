package ksm.navigation.state.name

import ksm.context.StateContext
import ksm.navigation.route.interceptRoute

public inline fun StateContext.interceptNamedRoute(
    string: String,
    block: () -> Unit
) {
    interceptRoute(
        predicate = { stateNameOrNull == string },
        block = block
    )
}
