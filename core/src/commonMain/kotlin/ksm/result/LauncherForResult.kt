package ksm.result

import ksm.typed.TypedValue

public interface LauncherForResult<in T> {
    public fun launch(value: TypedValue<T>)
}

public inline fun <reified T> LauncherForResult<T>.launch(data: T) {
    launch(TypedValue.of(data))
}
