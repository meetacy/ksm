package ksm.navigation.route.plugin

import ksm.context.StateContext

internal class StateRouteEntry : StateContext.Element {
    override val key = StateRouteEntry

    private var intercepted: Boolean = false

    fun routeIntercepted(): Boolean {
        return intercepted
    }

    fun interceptRoute() {
        if (intercepted) error("State was already intercepted")
        intercepted = true
    }

    companion object : StateContext.Key<StateRouteEntry>
}
