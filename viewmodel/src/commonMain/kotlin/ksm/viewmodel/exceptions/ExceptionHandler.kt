package ksm.viewmodel.exceptions

public fun interface ExceptionHandler {
    public fun handle(throwable: Throwable)
}
