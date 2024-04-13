package ksm.navigation.result

public fun interface NavigationResultHandler<in T> {
    public fun handle(result: T)
}
