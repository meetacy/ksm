package ksm.plugin.lifecycle

import ksm.context.StateContext
import ksm.plugin.lifecycle.interceptor.LifecycleInterceptor
import ksm.plugin.lifecycle.plugin.LifecyclePlugin
import ksm.plugin.plugin

public fun StateContext.addLifecycleInterceptor(interceptor: LifecycleInterceptor) {
    plugin(LifecyclePlugin).addLifecycleInterceptor(
        context = this,
        interceptor = interceptor
    )
}
