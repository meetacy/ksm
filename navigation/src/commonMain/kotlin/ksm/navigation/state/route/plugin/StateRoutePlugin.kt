package ksm.navigation.state.route.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.configuration.interceptor.ConfigurationInterceptor
import ksm.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.interceptor.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
import ksm.navigation.state.route.StateRouteScope
import ksm.navigation.state.route.interceptor.StateRouteInterceptor
import ksm.navigation.state.route.interceptor.plus
import ksm.plugin.Plugin

public class StateRoutePlugin(
    private val block: StateRouteScope.() -> Unit
) : Plugin {
    override val key: Companion = StateRoutePlugin

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration())
        return context
    }

    private inner class Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            context.addLifecycleInterceptor(Lifecycle())
            return context + StateRouteEntry()
        }
    }

    private inner class Lifecycle : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            val scope = StateRouteScope(context)
            block(scope)
            context.require(StateRouteEntry).interceptor?.onStateRoute(context)
            if (!scope.intercepted) {
                error("Cannot launch state because there is no handlers for this")
            }
        }
    }

    public fun addStateRouteInterceptor(
        context: StateContext,
        interceptor: StateRouteInterceptor
    ) {
        context.require(StateRouteEntry).interceptor += interceptor
    }

    public companion object : StateContext.Key<StateRoutePlugin>
}
