package ksm.navigation.state.route

import ksm.navigation.annotation.StateBuilderDSL
import ksm.context.StateContext

@StateBuilderDSL
public class StateRouteScope(
    public val stateRouteContextForFilter: StateContext
) {
    internal var intercepted = false

    public fun intercept() {
        require(!intercepted) { "State was already intercepted" }
        intercepted = true
    }

    public inline fun intercept(
        predicate: () -> Boolean,
        block: StateBuilderScope.() -> Unit
    ) {
        if (predicate()) {
            intercept()
            block(StateBuilderScope(stateRouteContextForFilter))
        }
    }
}
