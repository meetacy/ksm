package ksm.navigation.react.plugin

import ksm.navigation.react.ReactController
import react.ChildrenBuilder
import react.FC
import react.Props
import react.create

public fun interface ReactComponent {
    public fun ChildrenBuilder.component(controller: ReactController)
}

private external interface Props : Props {
    var controller: ReactController
}

public fun ReactComponent.createFunctionalComponent(controller: ReactController) {
    FC<ksm.navigation.react.plugin.Props> { props ->
        component(props.controller)
    }.create {
        this.controller = controller
    }
}
