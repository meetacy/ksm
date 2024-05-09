package ksm.navigation.compose.plugin

import androidx.compose.runtime.Composable
import ksm.StateController
import ksm.navigation.compose.ComposeController

public fun interface ComposeContent {
    @Composable
    public fun ComposeController.Content()
}

@Composable
public fun ComposeContent.Content(controller: ComposeController) {
    with (controller) { Content() }
}
