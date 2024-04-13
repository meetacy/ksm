package ksm.typed

import kotlin.reflect.KType
import kotlin.reflect.typeOf

public class TypedValue<out T>(
    public val data: T,
    public val type: KType
) {
    public fun interface Generic<out T> {
        public fun get(type: KType): T

        public companion object {
            public inline fun <reified T> of(data: T): Generic<T> {
                return of(TypedValue.of(data))
            }

            public fun <T> of(value: TypedValue<T>): Generic<T> {
                return Generic { type ->
                    require(type == value.type)
                    value.data
                }
            }
        }
    }

    public companion object {
        public inline fun <reified T> of(value: T): TypedValue<T> {
            return TypedValue(value, typeOf<T>())
        }
    }
}
