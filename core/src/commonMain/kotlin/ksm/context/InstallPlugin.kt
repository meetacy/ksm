package ksm.context

import ksm.annotation.MutateContext
import ksm.plugin.Plugin

@MutateContext
public fun StateContext.install(plugin: Plugin): StateContext {
    return plugin.install(context = this + plugin)
}
