package ksm.plugin.factory

import ksm.context.StateContext
import ksm.plugin.PluginController
import ksm.plugin.factory.plugin.ControllerFactoryPlugin
import ksm.plugin.plugin

public val StateContext.controllerFactory: StateControllerFactory
    get() = plugin(ControllerFactoryPlugin).factory

public inline fun <reified T : PluginController> StateContext.asController(): T {
    return controllerFactory.wrap(context = this) as T
}
