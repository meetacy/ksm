package ksm.navigation.compose.example

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ksm.StateController
import ksm.navigation.compose.Content
import ksm.navigation.compose.host.NavigationHost
import ksm.navigation.compose.rememberStateController
import ksm.navigation.destination.builder.DestinationRouteScope
import ksm.navigation.destination.builder.destinations
import ksm.navigation.destination.data.receive
import ksm.navigation.destination.name.named
import ksm.navigation.result.*
import ksm.viewmodel.viewModelStateController

private class MainViewModel {
    val actions: Flow<Action> = emptyFlow()

    val controller = viewModelStateController()

    sealed interface Action {
        data object RouteDetails : Action
    }
}

private data class DetailsParameters(val itemId: String)

private const val MAIN_SCREEN = "MainState"
private const val DETAILS_SCREEN = "DetailsState"

@Composable
private fun AppContent() {
    val controller = rememberStateController {
        destinations {
            main()
            details()
        }
    }

    NavigationHost(
        controller = controller,
        startDestinationName = MAIN_SCREEN
    )
}

private fun StateController.registerDetailsNavigator(
    handler: NavigationResultHandler<String>
): NavigatorForResult<DetailsParameters> {
    return registerNavigator(DETAILS_SCREEN, handler)
}

private fun DestinationRouteScope.main() = named(MAIN_SCREEN) {
    Content {
        val launcher = controller.registerDetailsNavigator { itemName ->
            println("Item Picked! $itemName")
        }

        // somewhere in actions handler
        val parameters = DetailsParameters(itemId = "10")
        launcher.navigate(parameters)
    }
}

private fun DestinationRouteScope.details() = named(DETAILS_SCREEN) {
    Content {
        val parameters: DetailsParameters = controller.receive()

        // somewhere in actions handler
        controller.finishWithResult("ItemName[${parameters.itemId}]")
    }
}
