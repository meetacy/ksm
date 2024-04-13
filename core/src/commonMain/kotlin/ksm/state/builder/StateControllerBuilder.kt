package ksm.state.builder

import ksm.builder.StateControllerBuilder
import ksm.context.StateContext
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor

public fun StateControllerBuilder.states(block: StateRouteScope.() -> Unit) {
    val interceptor = object : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            val scope = StateRouteScope(context)
            block(scope)
            if (!scope.intercepted) {
                error("Cannot launch state because there is no handlers for this")
            }
        }
    }
    context.addLifecycleInterceptor(interceptor)
}
