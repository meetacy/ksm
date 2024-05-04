package ksm.lifecycle.interceptor

import ksm.context.StateContext

internal class CombinedLifecycleInterceptor(
    private val first: LifecycleInterceptor?,
    private val second: LifecycleInterceptor
) : LifecycleInterceptor {
    override fun onCreate(context: StateContext) {
        first?.onCreate(context)
        second.onCreate(context)
    }

    override fun onPause(context: StateContext) {
        first?.onPause(context)
        second.onPause(context)
    }

    override fun onResume(context: StateContext) {
        first?.onResume(context)
        second.onResume(context)
    }

    override fun onFinish(context: StateContext) {
        first?.onFinish(context)
        second.onResume(context)
    }

    override fun onChildCreate(context: StateContext): StateContext {
        var applied = context
        applied = first?.onChildCreate(applied) ?: applied
        applied = second.onChildCreate(applied)
        return applied
    }
}
