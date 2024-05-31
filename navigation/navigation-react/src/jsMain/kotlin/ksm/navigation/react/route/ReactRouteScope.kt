package ksm.navigation.react.route

import ksm.context.StateContext
import ksm.navigation.annotation.StateRouteDSL
import ksm.navigation.route.StateRouteScope
import ksm.navigation.state.name.interceptNamedRoute

public fun ReactRouteScope(context: StateContext): ReactRouteScope {
    return object : ReactRouteScope {
        override val context = context
    }
}

@StateRouteDSL
public interface ReactRouteScope : StateRouteScope

public inline fun ReactRouteScope.named(name: String, block: ReactBuilderScope.() -> Unit) {
    context.interceptNamedRoute(name) {
        ReactBuilderScope(context).block()
    }
}
