package ksm.compose.plugin

import androidx.compose.runtime.Composable
import ksm.context.StateContext
import ksm.state.StateScope

internal class ComposeEntry : StateContext.Element {
    override val key = ComposeEntry

    var content: (@Composable StateScope.() -> Unit)? = null

    companion object : StateContext.Key<ComposeEntry>
}
