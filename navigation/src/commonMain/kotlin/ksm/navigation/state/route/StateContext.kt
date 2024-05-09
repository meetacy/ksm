package ksm.navigation.state.route

import ksm.context.StateContext
import ksm.navigation.state.route.plugin.StateRoutePlugin
import ksm.plugin.plugin

public fun StateContext.states(block: StateRouteScope.() -> Unit) {
    plugin(StateRoutePlugin).states(block)
}
