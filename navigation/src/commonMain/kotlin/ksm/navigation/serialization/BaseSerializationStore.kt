package ksm.navigation.serialization

import ksm.context.StateContext

public interface BaseSerializationStore {
    public suspend fun await() {}

    public interface String : BaseSerializationStore, StateContext.Element {
        override val key: Companion get() = String

        public fun get(): kotlin.String?
        public fun apply(string: kotlin.String)

        public companion object : StateContext.Key<String>
    }
}
