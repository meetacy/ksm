package ksm.viewmodel

import ksm.StateController
import ksm.annotation.LibraryConstructor
import ksm.asStateController
import ksm.builder.StateControllerBuilder
import ksm.context.StateContext
import ksm.context.createChildContext
import ksm.pluginStateController
import ksm.viewmodel.exceptions.plugin.ExceptionHandlerPlugin

@OptIn(LibraryConstructor::class)
public inline fun viewModelStateController(
    context: StateContext = StateContext.Empty,
    enableConfiguration: Boolean = true,
    enableLifecycle: Boolean = true,
    enableFinishOnce: Boolean = true,
    enableExceptionHandler: Boolean = true,
    builder: StateControllerBuilder.() -> Unit = {}
): StateController {
    val root = pluginStateController(
        context = context,
        enableConfiguration = enableConfiguration,
        enableLifecycle = enableLifecycle,
        enableFinishOnce = enableFinishOnce
    ) {
        if (enableExceptionHandler) install(ExceptionHandlerPlugin)
        builder()
    }.context

    // Calls onConfigure
    val built = root.createChildContext()

    return built.asStateController()
}
