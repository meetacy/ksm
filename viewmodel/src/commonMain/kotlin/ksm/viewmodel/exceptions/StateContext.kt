package ksm.viewmodel.exceptions

import ksm.context.StateContext
import ksm.viewmodel.exceptions.plugin.ExceptionHandlerPlugin
import ksm.plugin.plugin

public fun StateContext.setExceptionHandler(handler: ExceptionHandler) {
    plugin(ExceptionHandlerPlugin).setExceptionHandler(
        context = this,
        handler = handler
    )
}

public val StateContext.exceptionHandler: ExceptionHandler? get() {
    return plugin(ExceptionHandlerPlugin).exceptionHandler(context = this)
}
