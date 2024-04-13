package ksm.coroutines.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.Plugin
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.coroutines.interceptor.AwaitInterceptor
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public class CoroutinesPlugin(
    private val coroutineContext: CoroutineContext
) : Plugin {
    override val key: Companion = CoroutinesPlugin

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration())
        return context
    }

    private inner class Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            val entry = CoroutinesEntry(context, coroutineContext)
            context.addLifecycleInterceptor(Lifecycle(entry))
            return context + entry
        }
    }

    private inner class Lifecycle(val entry: CoroutinesEntry) : LifecycleInterceptor {
        override fun onFinish(context: StateContext) { entry.cancel() }
    }

    public fun coroutineContext(context: StateContext): CoroutineContext {
        return context.require(CoroutinesEntry).coroutineContext
    }

    public companion object : StateContext.Key<CoroutinesPlugin>, Plugin by CoroutinesPlugin(EmptyCoroutineContext)
}
