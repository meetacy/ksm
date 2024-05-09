package ksm.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext

public interface Plugin : StateContext.Element {
    override val key: StateContext.Key<*>

    @MutateContext
    public fun install(context: StateContext): StateContext

    public interface Singleton<out T : Singleton<T>> : Plugin, StateContext.Element.Singleton<T> {
        override val key: StateContext.Key<T> get() = this
    }
}
