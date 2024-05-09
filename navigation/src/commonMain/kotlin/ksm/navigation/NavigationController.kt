package ksm.navigation

import ksm.StateController
import ksm.pluginStateController
import ksm.annotation.LibraryConstructor
import ksm.builder.StateControllerBuilder
import ksm.context.StateContext
import ksm.navigation.state.name.plugin.StateNamePlugin
import ksm.navigation.state.parameters.plugin.StateParametersPlugin
import ksm.navigation.stack.installStackPlugin
import ksm.navigation.state.route.plugin.StateRoutePlugin

@LibraryConstructor
public inline fun navigationStateController(
    context: StateContext = StateContext.Empty,
    enableConfiguration: Boolean = true,
    enableLifecycle: Boolean = true,
    enableStateName: Boolean = true,
    enableStateParameters: Boolean = true,
    enableStack: Boolean = true,
    enableFinishOnce: Boolean = true,
    enableStateRoutePlugin: Boolean = true,
    block: StateControllerBuilder.() -> Unit = {}
): StateController {
    return pluginStateController(
        context = context,
        enableConfiguration = enableConfiguration,
        enableLifecycle = enableLifecycle,
        enableFinishOnce = enableFinishOnce
    ) {
        // Installing built-in features
        installStackPlugin(enableStack) {
            if (enableStateName) install(StateNamePlugin)
            if (enableStateParameters) install(StateParametersPlugin)
            if (enableStateRoutePlugin) install(StateRoutePlugin)
            block()
        }
    }
}
