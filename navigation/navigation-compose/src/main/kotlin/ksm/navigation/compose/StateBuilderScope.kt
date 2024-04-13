package ksm.navigation.compose

import androidx.compose.runtime.Composable
import ksm.navigation.compose.plugin.ComposePlugin
import ksm.navigation.destination.DestinationScope
import ksm.navigation.destination.builder.DestinationBuilderScope
import ksm.plugin.plugin

public fun DestinationBuilderScope.Content(
    block: @Composable DestinationScope.() -> Unit
) {
    context.plugin(ComposePlugin).setContent(context, block)
}
