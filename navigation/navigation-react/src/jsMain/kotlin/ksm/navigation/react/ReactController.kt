package ksm.navigation.react

import ksm.context.StateContext
import ksm.navigation.NavigationController
import ksm.plugin.factory.StateControllerFactory
import kotlin.reflect.KType
import kotlin.reflect.typeOf

public interface ReactController : NavigationController {
    public interface Builder : NavigationController.Builder

    public companion object : StateControllerFactory {
        override val type: KType = typeOf<ReactController>()

        override fun wrap(context: StateContext): ReactController {
            return object : ReactController {
                override val context = context
            }
        }
    }
}
