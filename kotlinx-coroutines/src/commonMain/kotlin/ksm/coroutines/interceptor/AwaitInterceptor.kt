package ksm.coroutines.interceptor

public fun interface AwaitInterceptor {
    public suspend fun await()
}
