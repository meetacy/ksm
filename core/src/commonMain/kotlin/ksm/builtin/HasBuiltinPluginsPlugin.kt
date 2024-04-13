package ksm.builtin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.plugin.Plugin

public object HasBuiltinPluginsPlugin : Plugin.Singleton<HasBuiltinPluginsPlugin> {

    // HasBuiltinPluginsPlugin is installed and added to context automatically
    @MutateContext
    override fun install(context: StateContext): StateContext = context
}
