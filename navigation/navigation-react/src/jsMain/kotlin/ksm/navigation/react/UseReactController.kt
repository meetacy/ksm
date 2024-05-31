package ksm.navigation.react

import ksm.annotation.LibraryApi
import ksm.context.StateContext
import react.useMemo

/**
 * Only works inside fc
 */
@OptIn(LibraryApi::class)
public fun useReactController(
    enableConfiguration: Boolean = true,
    enableLifecycle: Boolean = true,
    enableFinishOnce: Boolean = true,
    enableStateName: Boolean = true,
    enableStateParameters: Boolean = true,
    enableStack: Boolean = true,
    enableStateRoutePlugin: Boolean = true,
    builder: ReactController.Builder.() -> Unit = {}
): ReactController {
    return useMemo(
        enableConfiguration,
        enableLifecycle,
        enableFinishOnce,
        enableStateName,
        enableStateParameters,
        enableStack,
        enableStateRoutePlugin
    ) {
        val scope = object : ReactController.Builder {
            override var context: StateContext = StateContext.Empty
        }
        scope.reactRuntime(
            enableConfiguration = enableConfiguration,
            enableLifecycle = enableLifecycle,
            enableFinishOnce = enableFinishOnce,
            enableStateName = enableStateName,
            enableStateParameters = enableStateParameters,
            enableStack = enableStack,
            enableStateRoutePlugin = enableStateRoutePlugin
        ) {
            builder(scope)
        }
        ReactController.wrap(scope.context)
    }
}
