package ksm.navigation.compose.host

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ksm.StateController
import ksm.asStateController
import ksm.navigation.compose.plugin.ComposePlugin
import ksm.navigation.state.navigate
import ksm.plugin.plugin

@Composable
public fun NavigationStateHost(
    controller: StateController,
    startStateName: String,
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        val currentContext = remember(controller) {
            controller.navigate(startStateName)
            controller.context.plugin(ComposePlugin).currentContext
        }.value

        key(currentContext) {
            currentContext ?: return
            val childController = currentContext.asStateController()
            val content = currentContext.plugin(ComposePlugin).content(currentContext)
            content?.invoke(childController)
        }
    }
}
