package ksm.compose.host

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import ksm.StateController
import ksm.compose.plugin.ComposePlugin
import ksm.plugin.plugin
import ksm.state.StateScope
import ksm.state.launch

@Composable
public fun StateHost(
    controller: StateController,
    startStateName: String
) {
    Box {
        val currentContext = remember(controller) {
            controller.launch(startStateName)
            controller.context.plugin(ComposePlugin).currentContext
        }.value

        key(currentContext) {
            currentContext ?: return
            val childController = StateController(currentContext)
            val contentScope = StateScope(childController)
            val content = currentContext.plugin(ComposePlugin).content(currentContext)
            content?.invoke(contentScope)
        }
    }
}
