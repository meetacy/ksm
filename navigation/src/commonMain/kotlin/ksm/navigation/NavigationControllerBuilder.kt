package ksm.navigation

import ksm.annotation.LibraryApi
import ksm.navigation.stack.installStackPlugin
import ksm.navigation.state.name.installStateName
import ksm.navigation.state.parameters.installStateParameters
import ksm.navigation.state.route.installStateRoute
import ksm.plugin.factory.StateControllerFactory
import ksm.plugin.pluginRuntime

@LibraryApi
public inline fun NavigationController.Builder.navigationRuntime(
    controllerFactory: StateControllerFactory,
    enableConfiguration: Boolean = true,
    enableLifecycle: Boolean = true,
    enableFinishOnce: Boolean = true,
    enableStateName: Boolean = true,
    enableStateParameters: Boolean = true,
    enableStack: Boolean = true,
    enableStateRoutePlugin: Boolean = true,
    block: () -> Unit
) {
    pluginRuntime(
        controllerFactory = controllerFactory,
        enableConfiguration = enableConfiguration,
        enableLifecycle = enableLifecycle,
        enableFinishOnce = enableFinishOnce
    )
    installStackPlugin(enableStack) {
        if (enableStateName) installStateName()
        if (enableStateParameters) installStateParameters()
        if (enableStateRoutePlugin) installStateRoute()
        block()
    }
}
