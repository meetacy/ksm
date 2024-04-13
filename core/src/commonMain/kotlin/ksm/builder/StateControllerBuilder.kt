package ksm.builder

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.install
import ksm.plugin.Plugin

public class StateControllerBuilder(public var context: StateContext) {

    @OptIn(MutateContext::class)
    public fun install(plugin: Plugin) {
        context = context.install(plugin)
    }

    public fun build(): StateContext {
        return context
    }
}
