package ksm.viewmodel.exceptions

import ksm.StateController

public fun StateController.setExceptionHandler(handler: ExceptionHandler) {
    context.setExceptionHandler(handler)
}

public inline fun <T> StateController.handleExceptions(
    block: () -> Result<T>
): Result<T> {
    val result = block()
    result.onFailure { throwable ->
        context.exceptionHandler?.handle(throwable)
    }
    return result
}
