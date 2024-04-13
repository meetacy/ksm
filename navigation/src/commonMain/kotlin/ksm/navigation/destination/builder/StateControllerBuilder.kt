package ksm.navigation.destination.builder

import ksm.builder.StateControllerBuilder
import ksm.context.StateContext
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor

public fun StateControllerBuilder.destinations(block: DestinationRouteScope.() -> Unit) {
    val interceptor = object : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            val scope = DestinationRouteScope(context)
            block(scope)
            if (!scope.intercepted) {
                error("Cannot launch state because there is no handlers for this")
            }
        }
    }
    context.addLifecycleInterceptor(interceptor)
}
