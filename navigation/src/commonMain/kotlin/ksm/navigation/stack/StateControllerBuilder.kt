package ksm.navigation.stack

import ksm.annotation.LibraryApi
import ksm.context.StateContext
import ksm.navigation.NavigationController
import ksm.navigation.annotation.InstallStackPlugin
import ksm.navigation.stack.lifecycle.plugin.FirstStackLifecyclePlugin
import ksm.navigation.stack.lifecycle.plugin.LastStackLifecyclePlugin
import ksm.navigation.stack.plugin.StackPlugin
import ksm.plugin.install

@OptIn(InstallStackPlugin::class, LibraryApi::class)
public inline fun NavigationController.Builder.installStackPlugin(
    enabled: Boolean,
    block: () -> Unit
) {
    if (enabled) {
        install(FirstStackLifecyclePlugin)
        install(StackPlugin)
        block()
        install(LastStackLifecyclePlugin)
    } else {
        block()
    }
}
