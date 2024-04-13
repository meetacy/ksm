package ksm.exceptions

public fun interface ExceptionHandler {
    public fun handle(throwable: Throwable)
}
