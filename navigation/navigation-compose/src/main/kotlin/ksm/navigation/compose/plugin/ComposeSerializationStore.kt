package ksm.navigation.compose.plugin

import androidx.compose.runtime.saveable.SaverScope
import ksm.navigation.serialization.BaseSerializationStore
import androidx.compose.runtime.saveable.Saver as ComposeSaver

public class ComposeSerializationStore(
    internal var string: String? = null
) : BaseSerializationStore.String {

    override fun apply(string: String) {
        this.string = string
    }

    override fun get(): String? = string

    public object Saver : ComposeSaver<ComposeSerializationStore, String> {
        override fun restore(value: String): ComposeSerializationStore {
            return ComposeSerializationStore(value)
        }
        override fun SaverScope.save(value: ComposeSerializationStore): String? {
            return value.string
        }
    }
}
