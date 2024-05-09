package ksm.navigation.state.route.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.configuration.interceptor.ConfigurationInterceptor
import ksm.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.interceptor.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
import ksm.navigation.state.route.StateRouteScope
import ksm.plugin.Plugin

public object StateRoutePlugin : Plugin.Singleton<StateRoutePlugin> {
    // todo: вынести в StateRouteStateController
    private var block: (StateRouteScope.() -> Unit)? = null

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            context.addLifecycleInterceptor(Lifecycle)
            return context + StateRouteEntry()
        }
    }

    private object Lifecycle : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            val scope = StateRouteScope(context)
            val block = block ?: error("Please call `states` in StateControllerBuilder")
            block(scope)
            context.require(StateRouteEntry).interceptor?.onStateRoute(context)
            if (!scope.intercepted) {
                error("Cannot launch state because there is no handlers for this")
            }
        }
    }

    public fun states(block: StateRouteScope.() -> Unit) {
        this.block = block
    }
}
