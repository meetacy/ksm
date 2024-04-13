package ksm.navigation.result

import ksm.typed.TypedValue

public interface NavigatorForResult<in T> {
    public fun navigate(value: TypedValue<T>)
}

public inline fun <reified T> NavigatorForResult<T>.navigate(data: T) {
    navigate(TypedValue.of(data))
}
