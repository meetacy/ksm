package ksm.navigation.compose

import ksm.annotation.LibraryApi
import ksm.navigation.compose.plugin.ComposePlugin
import ksm.navigation.compose.plugin.ComposeSerializationStore
import ksm.navigation.compose.wrapper.ComposeWrapper
import ksm.navigation.navigationRuntime
import ksm.plugin.install

@LibraryApi
public inline fun ComposeController.Builder.composeRuntime(
    store: ComposeSerializationStore,
    wrapper: ComposeWrapper?,
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
        controllerFactory = ComposeController,
        enableConfiguration = enableConfiguration,
        enableLifecycle = enableLifecycle,
        enableFinishOnce = enableFinishOnce,
        enableStateName = enableStateName,
        enableStateParameters = enableStateParameters,
        enableStack = enableStack,
        enableStateRoutePlugin = enableStateRoutePlugin
    ) {
        install(ComposePlugin(store, wrapper))
        block()
    }
}
