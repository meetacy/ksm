package ksm.navigation.stack

import ksm.builder.StateControllerBuilder
import ksm.navigation.annotation.InstallStackPlugin
import ksm.navigation.stack.lifecycle.plugin.FirstStackLifecyclePlugin
import ksm.navigation.stack.lifecycle.plugin.LastStackLifecyclePlugin
import ksm.navigation.stack.plugin.StackPlugin

@OptIn(InstallStackPlugin::class)
public inline fun StateControllerBuilder.installStackPlugin(
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
