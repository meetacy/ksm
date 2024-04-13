package ksm

import ksm.annotation.LibraryConstructor
import ksm.builder.StateControllerBuilder
import ksm.context.StateContext
import ksm.context.configuration.plugin.ConfigurationPlugin
import ksm.context.finish
import ksm.finish.once.plugin.FinishOncePlugin
import ksm.lifecycle.plugin.LifecyclePlugin

@LibraryConstructor
public inline fun pluginStateController(
    context: StateContext = StateContext.Empty,
    enableConfiguration: Boolean = true,
    enableLifecycle: Boolean = true,
    enableFinishOnce: Boolean = true,
    builder: StateControllerBuilder.() -> Unit = {}
): StateController {
    return StateControllerBuilder(context).apply {
        if (enableConfiguration) install(ConfigurationPlugin)
        if (enableLifecycle) install(LifecyclePlugin)
        if (enableFinishOnce) install(FinishOncePlugin)

        builder()
    }.context.asStateController()
}

public interface StateController {
    public val context: StateContext
}

public fun StateController.finish() {
    context.finish()
}

public fun StateContext.asStateController(): StateController {
    return object : StateController {
        override val context = this@asStateController
    }
}
