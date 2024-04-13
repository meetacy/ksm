package ksm.compose

import androidx.compose.runtime.Composable
import ksm.compose.plugin.ComposePlugin
import ksm.plugin.plugin
import ksm.state.StateScope
import ksm.state.builder.StateBuilderScope

public fun StateBuilderScope.Content(
    block: @Composable StateScope.() -> Unit
) {
    context.plugin(ComposePlugin).setContent(context, block)
}
