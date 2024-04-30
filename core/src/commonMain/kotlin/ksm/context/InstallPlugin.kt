package ksm.context

import ksm.annotation.MutateContext
import ksm.plugin.Plugin

@MutateContext
public fun StateContext.install(plugin: Plugin): StateContext {
    if (plugin.key in this) {
        error("Plugin ${plugin::class.simpleName} is already installed")
    }
    return plugin.install(context = this + plugin)
}
