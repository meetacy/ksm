package ksm.navigation.serialization.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.navigation.state.parameters.interceptor.addParametersInterceptor
import ksm.plugin.Plugin
import ksm.navigation.serialization.BaseSerializationFormat
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.lifecycle.addLifecycleInterceptor
import ksm.plugin.lifecycle.interceptor.LifecycleInterceptor

public class BaseSerializationPlugin(format: BaseSerializationFormat) : Plugin {
    override val key: Companion = BaseSerializationPlugin
    private val controller = BaseSerializationStateController(format)

    @MutateContext
    override fun install(
        context: StateContext
    ): StateContext {
        context.addConfigurationInterceptor(Configuration())
        return context
    }

    private inner class Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            val parametersInterceptor = BaseSerializationParametersInterceptor()
            context.addParametersInterceptor(parametersInterceptor)
            context.addLifecycleInterceptor(Lifecycle(parametersInterceptor))
            return context
        }
    }

    private inner class Lifecycle(
        val interceptor: BaseSerializationParametersInterceptor
    ) : LifecycleInterceptor {
        override fun onResume(context: StateContext) {
            controller.commit(context, interceptor)
        }
    }

    public fun restore(context: StateContext) {
        controller.restore(context)
    }

    public companion object : StateContext.Key<BaseSerializationPlugin>
}
