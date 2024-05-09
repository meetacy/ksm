package ksm.navigation.result

import ksm.navigation.NavigationController

public fun <TData, TResult> NavigationController.registerNavigator(
    name: String,
    handler: NavigationResultHandler<TResult>
): NavigatorForResult<TData> {
    return context.registerNavigator(name, handler)
}

public fun NavigationController.finishWithResult(result: Any?) {
    context.finishWithResult(result)
}
