package ksm.navigation.react.route

import ksm.navigation.react.ReactController
import ksm.navigation.route.plugin.StateRoutePlugin
import ksm.plugin.plugin

public fun ReactController.states(block: ReactRouteScope.() -> Unit) {
    context.plugin(StateRoutePlugin).states(context) { context ->
        ReactRouteScope(context).apply(block)
    }
}
