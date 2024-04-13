package ksm.navigation.serialization

import ksm.typed.TypedValue
import kotlin.reflect.KType

public interface BaseSerializationFormat {
    public fun encode(value: TypedValue<*>)
    public fun decode(type: KType): Any?
}
