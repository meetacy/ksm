package ksm.lifecycle.plugin

import ksm.context.StateContext
import ksm.lifecycle.LifecycleInterceptor

internal class LifecycleEntry : StateContext.Element {
    override val key = LifecycleEntry

    private val observers = mutableListOf<LifecycleInterceptor>()

    fun addInterceptor(observer: LifecycleInterceptor) {
        observers += observer
    }

    fun onCreate(context: StateContext) {
        for (observer in observers) {
            observer.onCreate(context)
        }
    }
    fun onResume(context: StateContext) {
        for (observer in observers) {
            observer.onResume(context)
        }
    }
    fun onPause(context: StateContext) {
        for (observer in observers) {
            observer.onPause(context)
        }
    }
    fun onFinish(context: StateContext) {
        for (observer in observers) {
            observer.onFinish(context)
        }
    }

    companion object : StateContext.Key<LifecycleEntry>
}
