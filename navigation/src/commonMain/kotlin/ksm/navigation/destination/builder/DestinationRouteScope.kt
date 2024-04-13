package ksm.navigation.destination.builder

import ksm.navigation.annotation.StateBuilderDSL
import ksm.context.StateContext

@StateBuilderDSL
public class DestinationRouteScope(
    public val context: StateContext
) {
    internal var intercepted = false

    public fun intercept() {
        require(!intercepted) { "State was already intercepted" }
        intercepted = true
    }

    public inline fun intercept(
        predicate: () -> Boolean,
        block: DestinationBuilderScope.() -> Unit
    ) {
        if (predicate()) {
            intercept()
            block(DestinationBuilderScope(context))
        }
    }
}
