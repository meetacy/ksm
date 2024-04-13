package ksm.viewmodel.exceptions.plugin

import ksm.context.StateContext
import ksm.viewmodel.exceptions.ExceptionHandler

internal class ExceptionHandlerEntry(
    root: ExceptionHandlerEntry? = null
) : StateContext.Element {
    override val key = ExceptionHandlerEntry

    var handler: ExceptionHandler? = root?.handler

    companion object : StateContext.Key<ExceptionHandlerEntry>
}
