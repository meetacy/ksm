package ksm.coroutines

import kotlinx.coroutines.CoroutineScope
import ksm.annotation.LibraryApi
import ksm.coroutines.plugin.CoroutinesPlugin
import ksm.plugin.PluginController
import ksm.plugin.install

@OptIn(LibraryApi::class)
public fun PluginController.Builder.installCoroutines(scope: CoroutineScope) {
    install(CoroutinesPlugin(scope))
}
