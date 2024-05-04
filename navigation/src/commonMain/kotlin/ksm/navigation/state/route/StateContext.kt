package ksm.navigation.state.route

import ksm.context.StateContext
import ksm.navigation.state.route.interceptor.StateRouteInterceptor
import ksm.navigation.state.route.plugin.StateRoutePlugin
import ksm.plugin.plugin

public fun StateContext.addStateReadyInterceptor(interceptor: StateRouteInterceptor) {
    plugin(StateRoutePlugin).addStateRouteInterceptor(
        context = this,
        interceptor = interceptor
    )
}
