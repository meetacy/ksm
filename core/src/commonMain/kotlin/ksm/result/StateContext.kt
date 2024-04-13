package ksm.result

import ksm.context.StateContext
import ksm.plugin.plugin
import ksm.result.plugin.ResultPlugin

public fun <TData, TResult> StateContext.registerLauncher(
    name: String,
    handler: ResultHandler<TResult>
): LauncherForResult<TData> {
    return plugin(ResultPlugin).registerLauncher(
        context = this,
        name = name,
        handler = handler
    )
}

public fun StateContext.finishWithResult(result: Any?) {
    plugin(ResultPlugin).finishWithResult(
        context = this,
        result = result
    )
}
