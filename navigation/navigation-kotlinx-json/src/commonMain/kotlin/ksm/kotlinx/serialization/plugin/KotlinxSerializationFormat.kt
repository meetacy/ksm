package ksm.kotlinx.serialization.plugin

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import ksm.kotlinx.serialization.plugin.serializer.GenericValueDeserializer
import ksm.kotlinx.serialization.plugin.serializer.TypedValueSerializer
import ksm.navigation.serialization.BaseSerializationFormat
import ksm.navigation.serialization.BaseSerializationStore
import ksm.typed.TypedValue
import kotlin.reflect.KType

internal class KotlinxSerializationFormat(
    private val store: BaseSerializationStore.String,
    json: Json
) : BaseSerializationFormat {
    private val json = Json(json) {
        serializersModule = SerializersModule {
            include(serializersModule)
            contextual(TypedValue::class, TypedValueSerializer)
            contextual(TypedValue.Generic::class, GenericValueDeserializer)
        }
    }

    override fun encode(value: TypedValue) {
        val serializer = json.serializersModule.serializer(value.type)
        store.apply(json.encodeToString(serializer, value))
    }

    override fun decode(): TypedValue.Generic? {
        val string = store.get() ?: return null
        val element = json.parseToJsonElement(string)
        return TypedValue.Generic {  type ->
            val deserializer = json.serializersModule.serializer(type)
            json.decodeFromJsonElement(deserializer, element)
        }
    }
}
