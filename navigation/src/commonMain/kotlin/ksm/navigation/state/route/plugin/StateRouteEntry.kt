package ksm.navigation.state.route.plugin

import ksm.context.StateContext
import ksm.navigation.state.route.interceptor.StateRouteInterceptor

internal class StateRouteEntry : StateContext.Element {
    override val key = StateRouteEntry

    var interceptor: StateRouteInterceptor? = null

    companion object : StateContext.Key<StateRouteEntry>
}
