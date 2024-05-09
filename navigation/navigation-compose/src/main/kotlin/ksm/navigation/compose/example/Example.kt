package ksm.navigation.compose.example

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ksm.StateController
import ksm.navigation.NavigationController
import ksm.navigation.compose.Content
import ksm.navigation.compose.host.NavigationStateHost
import ksm.navigation.compose.rememberComposeController
import ksm.navigation.state.route.StateRouteScope
import ksm.navigation.state.route.states
import ksm.navigation.state.data.receive
import ksm.navigation.state.name.named
import ksm.navigation.result.*
import ksm.viewmodel.viewModelController
import ksm.viewmodel.viewModelRuntime

private class MainViewModel {
    val actions: Flow<Action> = emptyFlow()

    val controller = viewModelController()

    sealed interface Action {
        data object RouteDetails : Action
    }
}

private data class DetailsParameters(val itemId: String)

private const val MAIN_SCREEN = "MainState"
private const val DETAILS_SCREEN = "DetailsState"

@Composable
private fun AppContent() {
    val controller = rememberComposeController()

    controller.states {
        main()
        details()
    }

    NavigationStateHost(
        controller = controller,
        startStateName = MAIN_SCREEN
    )
}

private fun NavigationController.registerDetailsNavigator(
    handler: NavigationResultHandler<String>
): NavigatorForResult<DetailsParameters> {
    return registerNavigator(DETAILS_SCREEN, handler)
}

private fun StateRouteScope.main() = named(MAIN_SCREEN) {
    Content {
        val launcher = registerDetailsNavigator { itemName ->
            println("Item Picked! $itemName")
        }

        // somewhere in actions handler
        val parameters = DetailsParameters(itemId = "10")
        launcher.navigate(parameters)
    }
}

private fun StateRouteScope.details() = named(DETAILS_SCREEN) {
    Content {
        val parameters: DetailsParameters = receive()

        // somewhere in actions handler
        finishWithResult("ItemName[${parameters.itemId}]")
    }
}
