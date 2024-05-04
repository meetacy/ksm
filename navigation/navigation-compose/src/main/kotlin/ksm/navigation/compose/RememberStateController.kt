package ksm.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import ksm.StateController
import ksm.annotation.LibraryConstructor
import ksm.builder.StateControllerBuilder
import ksm.navigation.compose.plugin.ComposePlugin
import ksm.navigation.compose.plugin.ComposeSerializationStore
import ksm.navigation.compose.wrapper.ComposeWrapper
import ksm.navigation.navigationStateController
import ksm.navigation.serialization.restore

@OptIn(LibraryConstructor::class)
@Composable
public fun rememberStateController(
    wrapper: ComposeWrapper? = null,
    builder: StateControllerBuilder.() -> Unit
): StateController {
    val store = rememberSaveable(
        builder,
        saver = ComposeSerializationStore.Saver,
        init = { ComposeSerializationStore() }
    )
    return remember(store) {
        navigationStateController {
            install(ComposePlugin(store, wrapper))
            builder()
        }.apply {
            context.restore()
        }
    }
}
