package ksm.navigation.react

import ksm.annotation.LibraryApi
import ksm.context.StateContext
import ksm.navigation.react.observable.useObservableState
import react.useMemo
import react.useState

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
    val currentContextState = useState<StateContext?>(initialValue = null)
    val currentContext = useObservableState(currentContextState)

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
            currentContext = currentContext,
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
