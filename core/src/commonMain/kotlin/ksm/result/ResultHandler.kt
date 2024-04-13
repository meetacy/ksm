package ksm.result

public fun interface ResultHandler<in T> {
    public fun handle(result: T)
}
