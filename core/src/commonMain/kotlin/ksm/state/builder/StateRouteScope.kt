package ksm.state.builder

import ksm.annotation.StateBuilderDSL
import ksm.context.StateContext

@StateBuilderDSL
public class StateRouteScope(
    public val context: StateContext
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
            block(StateBuilderScope(context))
        }
    }
}
