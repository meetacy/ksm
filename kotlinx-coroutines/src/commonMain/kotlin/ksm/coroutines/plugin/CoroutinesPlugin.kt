package ksm.coroutines.plugin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.finish
import ksm.plugin.Plugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.lifecycle.addLifecycleInterceptor
import ksm.plugin.lifecycle.interceptor.LifecycleInterceptor

public class CoroutinesPlugin(
    private val scope: CoroutineScope
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
            val entry = CoroutinesEntry(context, scope)
            context.addLifecycleInterceptor(Lifecycle(entry))
            return context + entry
        }
    }

    private inner class Lifecycle(val entry: CoroutinesEntry) : LifecycleInterceptor {
        override fun onCreate(context: StateContext) {
            if (!entry.coroutineScope.isActive)
                error("Cannot start a new state because coroutineScope was already finished")
            entry.coroutineScope.launch {
                try {
                    awaitCancellation()
                } finally {
                    context.finish()
                }
            }
        }
        override fun onFinish(context: StateContext) { entry.cancel() }
    }

    public fun coroutineScope(context: StateContext): CoroutineScope {
        return context.require(CoroutinesEntry).coroutineScope
    }

    public companion object : StateContext.Key<CoroutinesPlugin>
}
