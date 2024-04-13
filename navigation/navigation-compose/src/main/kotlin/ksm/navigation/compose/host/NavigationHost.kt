package ksm.navigation.compose.host

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import ksm.StateController
import ksm.asStateController
import ksm.navigation.compose.plugin.ComposePlugin
import ksm.navigation.destination.DestinationScope
import ksm.navigation.destination.navigate
import ksm.plugin.plugin

@Composable
public fun NavigationHost(
    controller: StateController,
    startDestinationName: String
) {
    Box {
        val currentContext = remember(controller) {
            controller.navigate(startDestinationName)
            controller.context.plugin(ComposePlugin).currentContext
        }.value

        key(currentContext) {
            currentContext ?: return
            val childController = currentContext.asStateController()
            val contentScope = DestinationScope(childController)
            val content = currentContext.plugin(ComposePlugin).content(currentContext)
            content?.invoke(contentScope)
        }
    }
}