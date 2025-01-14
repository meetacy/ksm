package ksm.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import ksm.annotation.LibraryApi
import ksm.context.StateContext
import ksm.navigation.compose.plugin.ComposeSerializationStore
import ksm.navigation.compose.wrapper.ComposeWrapper
import ksm.navigation.serialization.restore

@OptIn(LibraryApi::class)
@Composable
public fun rememberComposeController(
    wrapper: ComposeWrapper? = null,
    builder: ComposeController.Builder.() -> Unit = {}
): ComposeController {
    val store = rememberSaveable(
        builder,
        saver = ComposeSerializationStore.Saver,
        init = { ComposeSerializationStore() }
    )
    return remember(store) {
        val scope = object : ComposeController.Builder {
            override var context: StateContext = StateContext.Empty
        }

        with(scope) {
            composeRuntime(store, wrapper) { builder() }
            context.restore()
        }

        ComposeController.wrap(scope.context)
    }
}
