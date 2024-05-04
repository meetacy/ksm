package ksm.lifecycle

import ksm.context.StateContext
import ksm.lifecycle.interceptor.LifecycleInterceptor
import ksm.lifecycle.plugin.LifecyclePlugin
import ksm.plugin.plugin

public fun StateContext.addLifecycleInterceptor(interceptor: LifecycleInterceptor) {
    plugin(LifecyclePlugin).addLifecycleInterceptor(
        context = this,
        interceptor = interceptor
    )
}
