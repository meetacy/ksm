package ksm.navigation.compose

import androidx.compose.runtime.Composable
import ksm.StateController
import ksm.navigation.compose.plugin.ComposePlugin
import ksm.navigation.state.route.StateBuilderScope
import ksm.plugin.plugin

public fun StateBuilderScope.Content(
    block: @Composable StateController.() -> Unit
) {
    context.plugin(ComposePlugin).setContent(context, block)
}
