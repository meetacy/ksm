package ksm.context

import ksm.annotation.MutateContext
import ksm.plugin.configuration.plugin.ConfigurationPlugin
import ksm.plugin.lifecycle.plugin.LifecyclePlugin

@OptIn(MutateContext::class)
public inline fun StateContext.createChildContext(
    setup: (StateContext) -> Unit = {}
): StateContext {
    var applied = this

    applied = applied[ConfigurationPlugin]?.onConfigure(applied) ?: applied

    applied = applied[LifecyclePlugin]?.onChildCreate(
        // this makes us to ignore newly
        // installed LifecycleInterceptors from onConfigure
        context = this,
        afterConfigure = applied
    ) ?: applied

    applied.apply(setup)

    applied[LifecyclePlugin]?.onCreate(applied)
    applied[LifecyclePlugin]?.onResume(applied)

    return applied
}
