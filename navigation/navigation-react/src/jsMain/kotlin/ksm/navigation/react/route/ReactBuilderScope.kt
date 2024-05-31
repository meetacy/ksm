package ksm.navigation.react.route

import ksm.context.StateContext
import ksm.navigation.annotation.StateRouteDSL
import ksm.navigation.react.plugin.ReactComponent
import ksm.navigation.react.plugin.ReactPlugin
import ksm.navigation.route.StateBuilderScope
import ksm.plugin.plugin
import react.FC
import react.Props
import react.create

public fun ReactBuilderScope(context: StateContext): ReactBuilderScope {
    return object : ReactBuilderScope {
        override val context = context
    }
}

@StateRouteDSL
public interface ReactBuilderScope : StateBuilderScope

public fun ReactBuilderScope.component(component: ReactComponent) {
    context.plugin(ReactPlugin).setComponent(
        context = context,
        component = component
    )
}

public fun ReactBuilderScope.component(fc: FC<Props>) {
    component { +fc.create() }
}
