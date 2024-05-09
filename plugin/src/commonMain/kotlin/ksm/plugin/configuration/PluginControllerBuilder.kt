package ksm.plugin.configuration

import ksm.annotation.LibraryApi
import ksm.plugin.PluginController
import ksm.plugin.configuration.plugin.ConfigurationPlugin
import ksm.plugin.install

@OptIn(LibraryApi::class)
public fun PluginController.Builder.installConfiguration() {
    install(ConfigurationPlugin)
}
