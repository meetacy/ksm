package ksm.navigation.react.example

import ksm.navigation.react.host.ReactStateHost
import ksm.navigation.react.host.ReactStateHostComponent
import ksm.navigation.react.route.component
import ksm.navigation.react.route.named
import ksm.navigation.react.route.states
import ksm.navigation.react.useReactController
import react.FC

private val exampleComponent = FC {
    val controller = useReactController()

    controller.states {
        named("FirstState") {
            component {

            }
        }
        named("SecondState") {
            component {

            }
        }
    }

    ReactStateHost(
        controller = controller,
        startStateName = "FirstState"
    )
}
