package ksm.kotlinx.serialization.plugin.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.serializer
import ksm.typed.TypedValue

public object GenericValueDeserializer : KSerializer<TypedValue.Generic> {
    @OptIn(ExperimentalSerializationApi::class)
    override val descriptor: SerialDescriptor = SerialDescriptor(
        serialName = "TypedValueSerializer",
        original = JsonElement.serializer().descriptor
    )

    override fun serialize(
        encoder: Encoder,
        value: TypedValue.Generic
    ) {
        error("TypedValue.Generic cannot be serialized, only deserialized. Use TypedValue instead")
    }

    override fun deserialize(decoder: Decoder): TypedValue.Generic {
        return TypedValue.Generic { type ->
            val deserializer = decoder.serializersModule.serializer(type)
            deserializer.deserialize(decoder)
        }
    }
}
