package ksm.viewmodel.exceptions

import ksm.annotation.LibraryApi
import ksm.plugin.install
import ksm.viewmodel.ViewModelController
import ksm.viewmodel.exceptions.plugin.ExceptionHandlerPlugin

@OptIn(LibraryApi::class)
public fun ViewModelController.Builder.installExceptionsHandler() {
    install(ExceptionHandlerPlugin)
}
