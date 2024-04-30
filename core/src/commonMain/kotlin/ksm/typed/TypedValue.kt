package ksm.typed

import kotlin.jvm.JvmName
import kotlin.reflect.KProperty
import kotlin.reflect.KType
import kotlin.reflect.typeOf

public class TypedValue(
    public val data: Any?,
    public val type: KType
) {
    public fun interface Generic {
        public fun get(type: KType): Any?

        public companion object {
            public inline fun <reified T> of(data: T): Generic {
                return of(TypedValue.of(data))
            }

            public fun of(value: TypedValue): Generic {
                return Generic { type ->
                    require(type == value.type)
                    value.data
                }
            }
        }
    }

    public companion object {
        public inline fun <reified T> of(value: T): TypedValue {
            return TypedValue(value, typeOf<T>())
        }
    }
}

public inline fun <reified T> TypedValue.Generic.get(): T {
    return get(typeOf<T>()) as T
}

@JvmName("getValueNotNull")
public inline operator fun <reified T> TypedValue.Generic.getValue(
    thisRef: Any?,
    property: KProperty<*>
): T { return get() }

public inline operator fun <reified T> TypedValue.Generic?.getValue(
    thisRef: Any?,
    property: KProperty<*>
): T? { return this?.get() }
