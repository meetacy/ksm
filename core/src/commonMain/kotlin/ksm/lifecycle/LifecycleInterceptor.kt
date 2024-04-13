package ksm.lifecycle

import ksm.context.StateContext

public interface LifecycleInterceptor {
    public fun onCreate(context: StateContext) {}
    public fun onResume(context: StateContext) {}
    public fun onPause(context: StateContext) {}
    public fun onFinish(context: StateContext) {}
}
