package ksm.navigation.compose.route

import ksm.context.StateContext
import ksm.navigation.route.StateRouteScope
import ksm.navigation.state.name.interceptNamedRoute

public fun ComposeRouteScope(context: StateContext): ComposeRouteScope {
    return object : ComposeRouteScope {
        override val context = context
    }
}

public interface ComposeRouteScope : StateRouteScope

public inline fun ComposeRouteScope.named(name: String, block: ComposeBuilderScope.() -> Unit) {
    context.interceptNamedRoute(name) { ComposeBuilderScope(context).block() }
}
