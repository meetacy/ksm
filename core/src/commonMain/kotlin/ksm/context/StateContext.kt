package ksm.context

import ksm.annotation.MutateContext

public interface StateContext {
    /**
     * Contract: Variable should be constant
     */
    public val keys: Set<Key<*>>

    public operator fun <T : Element> get(key: Key<T>): T?
    public fun <T : Element> require(key: Key<T>): T = get(key) ?: error("Cannot find element with key $key")

    public operator fun contains(key: Key<*>): Boolean = get(key) != null

    @MutateContext
    public operator fun plus(other: Element): StateContext {
        return plus(other.asContext())
    }

    @MutateContext
    public operator fun plus(other: StateContext): StateContext {
        if (this === Empty) return other
        return CombinedContext(left = this, right = other)
    }

    @MutateContext
    public operator fun minus(key: Key<*>): StateContext

    public interface Key<out T : Element>

    public interface Element {
        public val key: Key<*>

        public interface Singleton<out T : Singleton<T>> : Element, Key<T> {
            override val key: Key<T> get() = this
        }
    }

    public object Empty : StateContext {
        override val keys: Set<Key<*>> = emptySet()
        override fun <T : Element> get(key: Key<T>): T? = null
        @MutateContext
        override fun minus(key: Key<*>): StateContext = this
    }
}


public operator fun <T : StateContext.Element> StateContext?.get(key: StateContext.Key<T>): T? {
    return this?.get(key)
}

public operator fun StateContext?.contains(key: StateContext.Key<*>): Boolean {
    return this?.contains(key) ?: false
}

public fun StateContext?.orEmpty(): StateContext = this ?: StateContext.Empty
