package ksm.navigation.state.route

import ksm.navigation.NavigationController

public fun NavigationController.states(block: StateRouteScope.() -> Unit) {
    context.states(block)
}
