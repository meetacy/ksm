package ksm.kotlinx.serialization.plugin.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.serializer
import ksm.typed.TypedValue

public object TypedValueSerializer : KSerializer<TypedValue<*>> {
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor(
        serialName = "TypedValueSerializer",
        original = JsonElement.serializer().descriptor
    )

    override fun serialize(
        encoder: Encoder,
        value: TypedValue<*>
    ) {
        val baseSerializer = encoder.serializersModule.serializer(value.type)
        baseSerializer.serialize(encoder, value.data)
    }

    override fun deserialize(decoder: Decoder): TypedValue<*> {
        error("This type cannot be deserialized, use TypedValue.Generic<T> instead")
    }
}
