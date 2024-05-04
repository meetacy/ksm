package ksm.lifecycle.plugin

import ksm.context.StateContext
import ksm.lifecycle.interceptor.LifecycleInterceptor
import ksm.lifecycle.interceptor.plus

internal class LifecycleEntry : StateContext.Element {
    override val key = LifecycleEntry

    var interceptor: LifecycleInterceptor? = null

    companion object : StateContext.Key<LifecycleEntry>
}
