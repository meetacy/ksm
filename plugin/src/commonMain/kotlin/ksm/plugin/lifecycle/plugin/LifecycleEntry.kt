package ksm.plugin.lifecycle.plugin

import ksm.context.StateContext
import ksm.plugin.lifecycle.interceptor.LifecycleInterceptor

internal class LifecycleEntry : StateContext.Element {
    override val key = LifecycleEntry

    var interceptor: LifecycleInterceptor? = null

    companion object : StateContext.Key<LifecycleEntry>
}
