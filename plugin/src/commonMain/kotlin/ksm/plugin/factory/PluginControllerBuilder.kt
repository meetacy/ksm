package ksm.plugin.factory

import ksm.annotation.LibraryApi
import ksm.plugin.PluginController
import ksm.plugin.factory.plugin.ControllerFactoryPlugin
import ksm.plugin.install

@OptIn(LibraryApi::class)
public fun PluginController.Builder.installControllerFactory(factory: ControllerFactory) {
    install(ControllerFactoryPlugin(factory))
}
