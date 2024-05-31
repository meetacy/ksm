package ksm.navigation.react.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.navigation.react.observable.ObservableState
import ksm.plugin.Plugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.lifecycle.addLifecycleInterceptor
import ksm.plugin.lifecycle.interceptor.LifecycleInterceptor
import react.StateInstance

public object ReactPlugin : Plugin.Singleton<ReactPlugin> {

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context + ReactStateController()
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            context.addLifecycleInterceptor(Lifecycle)
            return context + ReactEntry()
        }
    }

    private object Lifecycle : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            context.require(ReactStateController).currentContext.value = context
        }
    }

    public fun setComponent(
        context: StateContext,
        component: ReactComponent
    ) {
        context.require(ReactEntry).component = component
    }

    public fun component(context: StateContext): ReactComponent {
        return context.require(ReactEntry).component ?: error("Please set 'component' in state builder")
    }

    public fun currentState(context: StateContext): ObservableState<StateContext?> {
        return context.require(ReactStateController).currentContext
    }
}
