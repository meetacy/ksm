package ksm.plugin.factory

import ksm.context.StateContext
import ksm.plugin.PluginController
import kotlin.reflect.KType

public interface StateControllerFactory {
    public val type: KType
    public fun wrap(context: StateContext = StateContext.Empty): PluginController
}
