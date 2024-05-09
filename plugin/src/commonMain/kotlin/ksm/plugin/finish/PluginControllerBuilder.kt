package ksm.plugin.finish

import ksm.annotation.LibraryApi
import ksm.plugin.PluginController
import ksm.plugin.finish.plugin.FinishOncePlugin
import ksm.plugin.install

@OptIn(LibraryApi::class)
public fun PluginController.Builder.installFinishOnce() {
    install(FinishOncePlugin)
}
