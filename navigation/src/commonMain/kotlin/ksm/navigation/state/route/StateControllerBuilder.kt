package ksm.navigation.state.route

import ksm.builder.StateControllerBuilder

public fun StateControllerBuilder.states(block: StateRouteScope.() -> Unit) {
    install(StateRoutePlugin(block))
}
