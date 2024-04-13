package ksm.compose.plugin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
import ksm.plugin.Plugin
import ksm.serialization.BaseSerializationStore
import ksm.state.StateScope

public class ComposePlugin(
    private val store: BaseSerializationStore.String
) : Plugin {
    override val key: Companion = ComposePlugin

    internal val currentContext = mutableStateOf<StateContext?>(value = null)

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration())
        return context + store
    }

    private inner class Configuration : ConfigurationInterceptor {

        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            context.addLifecycleInterceptor(Lifecycle())
            return context + ComposeEntry()
        }
    }

    private inner class Lifecycle : LifecycleInterceptor {
        override fun onResume(context: StateContext) {
            currentContext.value = context
        }
    }

    internal fun setContent(
        context: StateContext,
        content: @Composable StateScope.() -> Unit
    ) {
        context.require(ComposeEntry).content = content
    }

    internal fun content(context: StateContext): (@Composable StateScope.() -> Unit)? {
        return context.require(ComposeEntry).content
    }

    public companion object : StateContext.Key<ComposePlugin>
}
