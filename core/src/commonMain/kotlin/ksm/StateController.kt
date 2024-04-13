package ksm

import ksm.builder.StateControllerBuilder
import ksm.builtin.HasBuiltinPluginsPlugin
import ksm.context.StateContext
import ksm.context.configuration.plugin.ConfigurationPlugin
import ksm.context.finish
import ksm.finish.once.plugin.FinishOncePlugin
import ksm.lifecycle.plugin.LifecyclePlugin
import ksm.stack.plugin.StateStackPlugin
import ksm.state.name.plugin.StateNamePlugin
import ksm.state.parameters.plugin.StateParametersPlugin

public inline fun createRawStateController(
    context: StateContext = StateContext.Empty,
    enableStateName: Boolean = true,
    enableStateParameters: Boolean = true,
    enableConfiguration: Boolean = true,
    enableLifecycle: Boolean = true,
    enableStateStack: Boolean = true,
    enableFinishOnce: Boolean = true,
    builder: StateControllerBuilder.() -> Unit = {}
): StateController {
    var applied = context

    applied = StateControllerBuilder(applied).apply {
        if (HasBuiltinPluginsPlugin in context) return@apply

        // Installing built-in features
        if (enableStateName) install(StateNamePlugin)
        if (enableStateParameters) install(StateParametersPlugin)
        if (enableConfiguration) install(ConfigurationPlugin)
        if (enableLifecycle) install(LifecyclePlugin)
        if (enableStateStack) install(StateStackPlugin)
        if (enableFinishOnce) install(FinishOncePlugin)

        install(HasBuiltinPluginsPlugin)
    }.apply(builder).build()

    return object : StateController {
        override val context = applied
    }
}

public fun StateController(context: StateContext): StateController {
    return object : StateController {
        override val context = context
    }
}

public interface StateController {
    public val context: StateContext
}

public fun StateController.finish() {
    context.finish()
}
