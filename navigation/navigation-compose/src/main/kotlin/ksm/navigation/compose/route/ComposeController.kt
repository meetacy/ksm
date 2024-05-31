package ksm.navigation.compose.route

import ksm.navigation.compose.ComposeController
import ksm.navigation.route.plugin.StateRoutePlugin
import ksm.plugin.plugin

public fun ComposeController.states(block: ComposeRouteScope.() -> Unit) {
    context.plugin(StateRoutePlugin).states(context) { context ->
        ComposeRouteScope(context).apply(block)
    }
}
