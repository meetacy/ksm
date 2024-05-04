package ksm.navigation.compose.plugin

import androidx.compose.runtime.Composable
import ksm.StateController

public fun interface ComposeContent {
    @Composable
    public fun StateController.Content()
}

@Composable
public fun ComposeContent.Content(controller: StateController) {
    with (controller) { Content() }
}
