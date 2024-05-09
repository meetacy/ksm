package ksm.viewmodel

import ksm.annotation.LibraryApi
import ksm.plugin.pluginRuntime
import ksm.viewmodel.exceptions.installExceptionsHandler

@LibraryApi
public inline fun ViewModelController.Builder.viewModelRuntime(
    enableConfiguration: Boolean = true,
    enableLifecycle: Boolean = true,
    enableFinishOnce: Boolean = true,
    enableExceptionHandler: Boolean = true
) {
    pluginRuntime(ViewModelController, enableConfiguration, enableLifecycle, enableFinishOnce)
    if (enableExceptionHandler) installExceptionsHandler()
}
