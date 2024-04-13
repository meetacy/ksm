package ksm.result

import ksm.StateController

public fun <TData, TResult> StateController.registerLauncher(
    name: String,
    handler: ResultHandler<TResult>
): LauncherForResult<TData> {
    return context.registerLauncher(name, handler)
}

public fun StateController.finishWithResult(result: Any?) {
    context.finishWithResult(result)
}
