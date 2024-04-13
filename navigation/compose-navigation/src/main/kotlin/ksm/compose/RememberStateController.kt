package ksm.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import app.meetacy.di.DI
import app.meetacy.di.builder.di
import ksm.StateController
import ksm.builder.StateControllerBuilder
import ksm.compose.plugin.ComposePlugin
import ksm.compose.plugin.ComposeSerializationStore
import ksm.createRawStateController
import ksm.serialization.restore

@Composable
public fun rememberStateController(
    builder: StateControllerBuilder.() -> Unit
): StateController {
    val store = rememberSaveable(
        builder,
        saver = ComposeSerializationStore.Saver,
        init = { ComposeSerializationStore() }
    )
    return remember(store) {
        createRawStateController {
            install(ComposePlugin(store))
            builder()
        }.apply {
            context.restore()
        }
    }
}
