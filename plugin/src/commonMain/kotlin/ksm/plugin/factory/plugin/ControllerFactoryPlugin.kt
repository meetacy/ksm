package ksm.plugin.factory.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.plugin.Plugin
import ksm.plugin.factory.StateControllerFactory

public class ControllerFactoryPlugin(
    public val factory: StateControllerFactory
) : Plugin {
    override val key: Companion = ControllerFactoryPlugin

    @MutateContext
    override fun install(context: StateContext): StateContext {
        return context
    }

    public companion object : StateContext.Key<ControllerFactoryPlugin>
}