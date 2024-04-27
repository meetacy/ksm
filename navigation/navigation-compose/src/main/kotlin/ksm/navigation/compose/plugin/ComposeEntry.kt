package ksm.navigation.compose.plugin

import androidx.compose.runtime.Composable
import ksm.StateController
import ksm.context.StateContext

internal class ComposeEntry : StateContext.Element {
    override val key = ComposeEntry

    var content: (@Composable StateController.() -> Unit)? = null

    companion object : StateContext.Key<ComposeEntry>
}
