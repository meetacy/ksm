package ksm.navigation.state.route

import ksm.builder.StateControllerBuilder
import ksm.navigation.state.route.plugin.StateRoutePlugin

public fun StateControllerBuilder.states(block: StateRouteScope.() -> Unit) {
    install(StateRoutePlugin(block))
}
