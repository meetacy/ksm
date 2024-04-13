package ksm.navigation.compose.host

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import ksm.StateController
import ksm.asStateController
import ksm.navigation.compose.plugin.ComposePlugin
import ksm.navigation.state.StateScope
import ksm.navigation.state.navigate
import ksm.plugin.plugin

@Composable
public fun NavigationHost(
    controller: StateController,
    startStateName: String
) {
    Box {
        val currentContext = remember(controller) {
            controller.navigate(startStateName)
            controller.context.plugin(ComposePlugin).currentContext
        }.value

        key(currentContext) {
            currentContext ?: return
            val childController = currentContext.asStateController()
            val contentScope = StateScope(childController)
            val content = currentContext.plugin(ComposePlugin).content(currentContext)
            content?.invoke(contentScope)
        }
    }
}
