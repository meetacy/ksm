package ksm.exceptions

import ksm.StateController

public fun StateController.setExceptionHandler(handler: ExceptionHandler) {
    context.setExceptionHandler(handler)
}

public inline fun StateController.handleExceptions(block: () -> Unit) {
    try {
        block()
    } catch (throwable: Throwable) {
        context.exceptionHandler
            ?.handle(throwable)
            ?: throw throwable
    }
}
