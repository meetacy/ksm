package ksm.compose.example

import ksm.StateController
import ksm.exceptions.handleExceptions
import ksm.exceptions.setExceptionHandler

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import app.meetacy.di.DI
import ksm.mdi.plugin.DIPlugin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ksm.compose.Content
import ksm.compose.host.StateHost
import ksm.compose.rememberStateController
import ksm.kotlinx.serialization.plugin.KotlinxSerializationPlugin
import ksm.result.*
import ksm.state.builder.StateRouteScope
import ksm.state.builder.states
import ksm.state.data.receive
import ksm.state.name.named

private class MainViewModel(val controller: StateController) {
    val actions: Flow<Action> = emptyFlow()

    init {
        controller.handleExceptions {
            // ...
        }
    }

    sealed interface Action {
        data object RouteDetails : Action
    }
}

private data class DetailsParameters(val itemId: String)

private const val MAIN_STATE = "MainState"
private const val DETAILS_STATE = "DetailsState"

@Composable
private fun AppContent(di: DI) {
    val controller = rememberStateController {
        install(KotlinxSerializationPlugin())
        install(DIPlugin(di))

        states {
            main()
            details()
        }
    }

    controller.setExceptionHandler { throwable ->

    }

    StateHost(
        controller = controller,
        startStateName = MAIN_STATE
    )
}


private fun StateController.registerDetailsLauncher(
    handler: ResultHandler<String>
): LauncherForResult<DetailsParameters> {
    return registerLauncher(DETAILS_STATE, handler)
}

private fun StateRouteScope.main() = named(MAIN_STATE) {
    Content {
        val launcher = controller.registerDetailsLauncher { itemName ->
            println("Item Picked! $itemName")
        }

        // somewhere in actions handler
        val parameters = DetailsParameters(itemId = "10")
        launcher.launch(parameters)
    }
}

private fun StateRouteScope.details() = named(DETAILS_STATE) {
    Content {
        val parameters: DetailsParameters = controller.receive()

        // somewhere in actions handler
        controller.finishWithResult("ItemName[${parameters.itemId}]")
    }
}
