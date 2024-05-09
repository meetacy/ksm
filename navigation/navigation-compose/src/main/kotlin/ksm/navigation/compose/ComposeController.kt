package ksm.navigation.compose

import ksm.context.StateContext
import ksm.navigation.NavigationController
import ksm.plugin.PluginController
import ksm.plugin.factory.ControllerFactory
import kotlin.reflect.KType
import kotlin.reflect.typeOf

public interface ComposeController : NavigationController {
    public interface Builder : NavigationController.Builder

    public companion object : ControllerFactory {
        override val type: KType = typeOf<ComposeController>()

        override fun wrap(context: StateContext): PluginController {
            return object : ComposeController {
                override val context = context
            }
        }
    }
}
