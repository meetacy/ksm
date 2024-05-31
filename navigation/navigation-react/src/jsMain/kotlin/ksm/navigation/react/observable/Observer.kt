package ksm.navigation.react.observable

public fun interface Observer<in T> {
    public fun accept(value: T)
}
