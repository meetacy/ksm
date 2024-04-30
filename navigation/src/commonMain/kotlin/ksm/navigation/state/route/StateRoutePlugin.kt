package ksm.navigation.state.route

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
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
            return context
        }
    }

    private inner class Lifecycle : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            val scope = StateRouteScope(context)
            block(scope)
            if (!scope.intercepted) {
                error("Cannot launch state because there is no handlers for this")
            }
        }
    }

    public companion object : StateContext.Key<StateRoutePlugin>
}
