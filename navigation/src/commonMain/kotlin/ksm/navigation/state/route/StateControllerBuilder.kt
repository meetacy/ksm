package ksm.navigation.state.route

import ksm.builder.StateControllerBuilder
import ksm.navigation.state.route.plugin.StateRoutePlugin
import ksm.plugin.plugin

public fun StateControllerBuilder.states(block: StateRouteScope.() -> Unit) {
    context.plugin(StateRoutePlugin).states(block)
}
