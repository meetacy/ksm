package ksm.plugin.lifecycle.interceptor

import ksm.context.StateContext

public interface LifecycleInterceptor {
    public fun onCreate(context: StateContext) {}
    public fun onResume(context: StateContext) {}
    public fun onPause(context: StateContext) {}
    public fun onFinish(context: StateContext) {}
    public fun onChildCreate(context: StateContext): StateContext = context
}

public operator fun LifecycleInterceptor?.plus(other: LifecycleInterceptor): LifecycleInterceptor {
    return CombinedLifecycleInterceptor(
        first = this,
        second = other
    )
}
