package ksm.navigation.compose

import androidx.compose.runtime.Composable
import ksm.navigation.compose.plugin.ComposePlugin
import ksm.navigation.state.StateScope
import ksm.navigation.state.builder.StateBuilderScope
import ksm.plugin.plugin

public fun StateBuilderScope.Content(
    block: @Composable StateScope.() -> Unit
) {
    context.plugin(ComposePlugin).setContent(context, block)
}
