package ksm.navigation.route.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.navigation.route.StateRouteScope
import ksm.navigation.route.routeIntercepted
import ksm.plugin.Plugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.lifecycle.addLifecycleInterceptor
import ksm.plugin.lifecycle.interceptor.LifecycleInterceptor

public object StateRoutePlugin : Plugin.Singleton<StateRoutePlugin> {

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context + StateRouteStateController()
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
            val block = context.require(StateRouteStateController).block()
            block(context)
            if (!context.routeIntercepted) {
                error("Cannot launch state because there is no handlers")
            }
        }
    }

    public fun states(
        context: StateContext,
        block: (StateContext) -> Unit
    ) {
        context.require(StateRouteStateController).setBlock(block)
    }

    public fun routeIntercepted(context: StateContext): Boolean {
        return context.require(StateRouteEntry).routeIntercepted()
    }

    public fun interceptRoute(context: StateContext) {
        context.require(StateRouteEntry).interceptRoute()
    }
}
