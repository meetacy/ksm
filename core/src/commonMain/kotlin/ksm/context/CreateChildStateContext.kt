package ksm.context

import ksm.annotation.MutateContext
import ksm.configuration.plugin.ConfigurationPlugin
import ksm.lifecycle.plugin.LifecyclePlugin

@OptIn(MutateContext::class)
public inline fun StateContext.createChildContext(
    setup: (StateContext) -> Unit = {}
): StateContext {
    var applied = this

    applied = this[ConfigurationPlugin]?.onConfigure(applied) ?: applied

    // intentionally using this[LifecyclePlugin], not applied[LifecyclePlugin]
    // in order to ignore lifecycle interceptors installed in onConfigure
    applied = this[LifecyclePlugin]?.onChildCreate(applied) ?: applied

    applied.apply(setup)

    applied[LifecyclePlugin]?.onCreate(applied)
    applied[LifecyclePlugin]?.onResume(applied)

    return applied
}
