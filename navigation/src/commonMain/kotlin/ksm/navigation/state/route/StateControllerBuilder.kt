package ksm.navigation.state.route

import ksm.builder.StateControllerBuilder
import ksm.context.StateContext
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
import ksm.navigation.state.route.StateRouteScope

public fun StateControllerBuilder.states(block: StateRouteScope.() -> Unit) {
    install(StateRoutePlugin(block))
}
