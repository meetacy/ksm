package ksm.navigation.state.route.interceptor

import ksm.context.StateContext

internal class CombinedStateRouteInterceptor(
    private val first: StateRouteInterceptor?,
    private val second: StateRouteInterceptor
) : StateRouteInterceptor {
    override fun onStateRoute(context: StateContext) {
        first?.onStateRoute(context)
        second.onStateRoute(context)
    }
}
