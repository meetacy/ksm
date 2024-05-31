package ksm.navigation.react

import ksm.annotation.LibraryApi
import ksm.context.StateContext
import ksm.navigation.navigationRuntime
import ksm.navigation.react.observable.ObservableState
import ksm.navigation.react.plugin.ReactPlugin
import ksm.plugin.install

@LibraryApi
public inline fun ReactController.Builder.reactRuntime(
    currentContext: ObservableState<StateContext?>,
    enableConfiguration: Boolean = true,
    enableLifecycle: Boolean = true,
    enableFinishOnce: Boolean = true,
    enableStateName: Boolean = true,
    enableStateParameters: Boolean = true,
    enableStack: Boolean = true,
    enableStateRoutePlugin: Boolean = true,
    block: () -> Unit
) {
    navigationRuntime(
        controllerFactory = ReactController,
        enableConfiguration = enableConfiguration,
        enableLifecycle = enableLifecycle,
        enableFinishOnce = enableFinishOnce,
        enableStateName = enableStateName,
        enableStateParameters = enableStateParameters,
        enableStack = enableStack,
        enableStateRoutePlugin = enableStateRoutePlugin
    ) {
        install(ReactPlugin)
        block()
    }
}
