package ksm.navigation.compose

import androidx.compose.runtime.Composable
import ksm.navigation.compose.plugin.ComposePlugin
import ksm.navigation.route.StateBuilderScope
import ksm.plugin.plugin

public fun StateBuilderScope.Content(
    block: @Composable ComposeController.() -> Unit
) {
    context.plugin(ComposePlugin).setContent(context, block)
}
