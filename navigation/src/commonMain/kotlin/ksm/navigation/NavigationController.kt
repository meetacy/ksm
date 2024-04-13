package ksm.navigation

import ksm.StateController
import ksm.pluginStateController
import ksm.annotation.LibraryConstructor
import ksm.builder.StateControllerBuilder
import ksm.context.StateContext
import ksm.navigation.destination.name.plugin.DestinationNamePlugin
import ksm.navigation.destination.parameters.plugin.DestinationParametersPlugin
import ksm.navigation.stack.installStackPlugin

@LibraryConstructor
public inline fun navigationStateController(
    context: StateContext = StateContext.Empty,
    enableConfiguration: Boolean = true,
    enableLifecycle: Boolean = true,
    enableDestinationName: Boolean = true,
    enableDestinationParameters: Boolean = true,
    enableStack: Boolean = true,
    enableFinishOnce: Boolean = true,
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
            if (enableDestinationName) install(DestinationNamePlugin)
            if (enableDestinationParameters) install(DestinationParametersPlugin)
            block()
        }
    }
}
