package ksm.kotlinx.serialization.plugin

import kotlinx.serialization.json.Json
import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.install
import ksm.navigation.serialization.BaseSerializationStore
import ksm.navigation.serialization.plugin.BaseSerializationPlugin
import ksm.plugin.Plugin

public class KotlinxSerializationPlugin(
    private val json: Json = Json,
    private val store: BaseSerializationStore.String? = null
) : Plugin {
    override val key: Companion = KotlinxSerializationPlugin

    @MutateContext
    override fun install(context: StateContext): StateContext {
        val store = this.store
            ?: context[BaseSerializationStore.String]
            ?: error("Please either provide BaseSerializationStore using constructor or install the plugin that will provide it")
        val format = KotlinxSerializationFormat(store, json)
        val basePlugin = BaseSerializationPlugin(format)
        return context.install(basePlugin)
    }

    public companion object : StateContext.Key<KotlinxSerializationPlugin>
}
