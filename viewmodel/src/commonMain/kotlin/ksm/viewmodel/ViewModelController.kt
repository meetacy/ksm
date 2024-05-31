package ksm.viewmodel

import ksm.annotation.LibraryApi
import ksm.context.StateContext
import ksm.context.createChildContext
import ksm.plugin.PluginController
import ksm.plugin.factory.StateControllerFactory
import ksm.plugin.factory.asController
import kotlin.reflect.KType
import kotlin.reflect.typeOf

public interface ViewModelController : PluginController {
    public interface Builder : PluginController.Builder

    public companion object : StateControllerFactory {
        override val type: KType = typeOf<ViewModelController>()

        override fun wrap(context: StateContext): ViewModelController {
            return object : ViewModelController { override val context = context }
        }
    }
}

@OptIn(LibraryApi::class)
public inline fun viewModelController(
    context: StateContext = StateContext.Empty,
    enableConfiguration: Boolean = true,
    enableLifecycle: Boolean = true,
    enableFinishOnce: Boolean = true,
    enableExceptionHandler: Boolean = true,
    block: ViewModelController.Builder.() -> Unit = {}
): ViewModelController {
    val scope = object : ViewModelController.Builder {
        override var context = context
    }

    with(scope) {
        viewModelRuntime(
            enableConfiguration = enableConfiguration,
            enableLifecycle = enableLifecycle,
            enableFinishOnce = enableFinishOnce,
            enableExceptionHandler = enableExceptionHandler
        )
        block()
    }

    // Calls onConfigure
    val built = scope.context.createChildContext()

    return ViewModelController.wrap(built)
}
