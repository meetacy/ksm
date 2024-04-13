package ksm.context

import ksm.annotation.MutateContext

/**
 * [right] elements overwrite [left] elements
 */
internal class CombinedContext(
    val left: StateContext,
    val right: StateContext
) : StateContext {
    override val keys = left.keys + right.keys

    override fun <T : StateContext.Element> get(key: StateContext.Key<T>): T? {
        return right[key] ?: left[key]
    }

    @MutateContext
    override fun minus(key: StateContext.Key<*>): StateContext {
        return CombinedContext(left.minus(key), right.minus(key))
    }
}
