package ksm.context

import ksm.annotation.MutateContext

public fun StateContext.Element.asContext(): StateContext {
    return SingleElementContext(element = this)
}

private class SingleElementContext(val element: StateContext.Element): StateContext {
    override val keys = setOf(element.key)

    @Suppress("UNCHECKED_CAST")
    override fun <T : StateContext.Element> get(key: StateContext.Key<T>): T? {
        return if (element.key == key) (this as T) else null
    }

    @MutateContext
    override fun minus(key: StateContext.Key<*>): StateContext {
        return if (key == element.key) {
            StateContext.Empty
        } else {
            this
        }
    }
}
