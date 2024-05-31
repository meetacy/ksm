package ksm.navigation.compose.host

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import ksm.StateController
import ksm.navigation.compose.ComposeController
import ksm.navigation.compose.plugin.ComposePlugin
import ksm.navigation.state.navigate
import ksm.plugin.factory.asController
import ksm.plugin.plugin

@Composable
public fun ComposeStateHost(
    controller: ComposeController,
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
            val childController: ComposeController = currentContext.asController()
            val content = currentContext.plugin(ComposePlugin).content(currentContext)
            if (content != null) {
                with (content) {
                    childController.Content()
                }
            }
        }
    }
}
