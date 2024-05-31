package ksm.navigation.route

import ksm.context.StateContext
import ksm.navigation.route.plugin.StateRoutePlugin
import ksm.plugin.plugin

public val StateContext.routeIntercepted: Boolean get() {
    return plugin(StateRoutePlugin).routeIntercepted(context = this)
}

public fun StateContext.interceptRoute() {
    plugin(StateRoutePlugin).interceptRoute(context = this)
}

public inline fun StateContext.interceptRoute(
    predicate: () -> Boolean,
    block: () -> Unit
) {
    if (predicate()) {
        interceptRoute()
        block()
    }
}

