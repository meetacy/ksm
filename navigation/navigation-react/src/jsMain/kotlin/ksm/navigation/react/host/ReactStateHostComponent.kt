package ksm.navigation.react.host

import ksm.navigation.react.ReactController
import ksm.navigation.react.observable.useState
import ksm.navigation.react.plugin.ReactPlugin
import ksm.navigation.react.plugin.createFunctionalComponent
import ksm.navigation.state.navigate
import ksm.plugin.factory.asController
import ksm.plugin.plugin
import org.w3c.dom.HTMLStyleElement
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.style

public val ReactStateHostComponent: FC<ReactControllerProps> = FC { props ->
    val controller = props.controller

    useEffectOnce {
        controller.navigate(props.startStateName)
    }

    div {
        style(props.style)

        val plugin = controller.context.plugin(ReactPlugin)

        val (currentContext) = useState(plugin.currentState(controller.context))
        currentContext ?: return@div

        val component = plugin.component(currentContext)
        val fc = component.createFunctionalComponent(currentContext.asController())
        +fc
    }
}

@Suppress("FunctionName")
public fun ChildrenBuilder.ReactStateHost(
    controller: ReactController,
    startStateName: String,
    style: (HTMLStyleElement) -> Unit = {}
) {
    +ReactStateHostComponent.create {
        this.controller = controller
        this.startStateName = startStateName
        this.style = style
    }
}
