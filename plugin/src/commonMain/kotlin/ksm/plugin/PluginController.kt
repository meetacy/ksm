package ksm.plugin

import ksm.StateController
import ksm.annotation.LibraryApi
import ksm.annotation.MutateContext

public interface PluginController : StateController {
    public interface Builder : StateController.Builder
}

@LibraryApi
@OptIn(MutateContext::class)
public fun PluginController.Builder.install(plugin: Plugin) {
    context = context.install(plugin)
}
