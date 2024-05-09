package ksm.plugin.lifecycle

import ksm.annotation.LibraryApi
import ksm.plugin.PluginController
import ksm.plugin.install
import ksm.plugin.lifecycle.plugin.LifecyclePlugin

@OptIn(LibraryApi::class)
public fun PluginController.Builder.installLifecycle() {
    install(LifecyclePlugin)
}
