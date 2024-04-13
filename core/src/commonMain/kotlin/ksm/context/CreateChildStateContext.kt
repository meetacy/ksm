package ksm.context

import ksm.annotation.MutateContext
import ksm.context.configuration.plugin.ConfigurationPlugin
import ksm.lifecycle.plugin.LifecyclePlugin

@OptIn(MutateContext::class)
public inline fun StateContext.createChildContext(
    setup: (StateContext) -> Unit
): StateContext {
    this[LifecyclePlugin]?.onPause(context = this)

    val applied = this[ConfigurationPlugin]
        ?.onConfigure(context = this)
        ?: this

    applied.apply(setup)

    applied[LifecyclePlugin]?.onCreate(applied)
    applied[LifecyclePlugin]?.onResume(applied)

    return applied
}
