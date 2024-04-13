package ksm.navigation.compose.plugin

import androidx.compose.runtime.Composable
import ksm.context.StateContext
import ksm.navigation.destination.DestinationScope

internal class ComposeEntry : StateContext.Element {
    override val key = ComposeEntry

    var content: (@Composable DestinationScope.() -> Unit)? = null

    companion object : StateContext.Key<ComposeEntry>
}
