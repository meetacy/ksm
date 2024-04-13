package ksm.navigation.result

import ksm.context.StateContext
import ksm.plugin.plugin
import ksm.navigation.result.plugin.NavigationResultPlugin

public fun <TData, TResult> StateContext.registerNavigator(
    name: String,
    handler: NavigationResultHandler<TResult>
): NavigatorForResult<TData> {
    return plugin(NavigationResultPlugin).registerNavigator(
        context = this,
        name = name,
        handler = handler
    )
}

public fun StateContext.finishWithResult(result: Any?) {
    plugin(NavigationResultPlugin).finishWithResult(
        context = this,
        result = result
    )
}
