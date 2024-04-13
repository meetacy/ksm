package ksm.navigation.result

import ksm.StateController

public fun <TData, TResult> StateController.registerNavigator(
    name: String,
    handler: NavigationResultHandler<TResult>
): NavigatorForResult<TData> {
    return context.registerNavigator(name, handler)
}

public fun StateController.finishWithResult(result: Any?) {
    context.finishWithResult(result)
}
