package ksm.navigation.serialization

import ksm.typed.TypedValue
import kotlin.reflect.KType
import kotlin.reflect.typeOf

public interface BaseSerializationFormat {
    public fun encode(value: TypedValue)
    public fun decode(): TypedValue.Generic?
}
