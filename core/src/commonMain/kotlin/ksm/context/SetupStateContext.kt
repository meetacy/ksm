package ksm.context

import ksm.annotation.MutateContext

@MutateContext
public inline fun StateContext.plus(
    vararg elements: StateContext.Element,
    block: StateContext.() -> Unit = {}
): StateContext {
    val result = elements.fold(
        initial = this,
        operation = StateContext::plus
    )
    block(result)
    return result
}
