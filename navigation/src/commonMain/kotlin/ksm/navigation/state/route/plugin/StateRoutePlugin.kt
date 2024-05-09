package ksm.navigation.state.route.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.navigation.state.route.StateRouteScope
import ksm.plugin.Plugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.lifecycle.addLifecycleInterceptor
import ksm.plugin.lifecycle.interceptor.LifecycleInterceptor

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
            return context
        }
    }

    private object Lifecycle : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            val scope = StateRouteScope(context)
            val block = block ?: error("Please call `states` in StateControllerBuilder")
            block(scope)
            if (!scope.intercepted) {
                error("Cannot launch state because there is no handlers for this")
            }
        }
    }

    public fun states(block: StateRouteScope.() -> Unit) {
        this.block = block
    }
}
