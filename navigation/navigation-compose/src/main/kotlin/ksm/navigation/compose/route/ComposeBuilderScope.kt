package ksm.navigation.compose.route

import androidx.compose.runtime.Composable
import ksm.context.StateContext
import ksm.navigation.compose.ComposeController
import ksm.navigation.compose.plugin.ComposePlugin
import ksm.navigation.route.StateBuilderScope
import ksm.plugin.plugin

public fun ComposeBuilderScope(context: StateContext): ComposeBuilderScope {
    return object : ComposeBuilderScope {
        override val context = context
    }
}

public interface ComposeBuilderScope : StateBuilderScope

@Suppress("FunctionName")
public fun ComposeBuilderScope.Content(content: @Composable ComposeController.() -> Unit) {
    context.plugin(ComposePlugin).setContent(
        context = context,
        content = content
    )
}
