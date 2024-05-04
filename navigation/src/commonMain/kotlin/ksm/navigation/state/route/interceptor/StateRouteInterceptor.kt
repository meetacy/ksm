package ksm.navigation.state.route.interceptor

import ksm.context.StateContext

public fun interface StateRouteInterceptor {
    // Called after StateBuilderScope was built
    public fun onStateRoute(context: StateContext)
}

public operator fun StateRouteInterceptor?.plus(other: StateRouteInterceptor): StateRouteInterceptor {
    return CombinedStateRouteInterceptor(
        first = this,
        second = other
    )
}
