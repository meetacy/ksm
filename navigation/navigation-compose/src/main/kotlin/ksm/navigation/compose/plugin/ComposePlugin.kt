package ksm.navigation.compose.plugin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import ksm.annotation.LibraryApi
import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.navigation.compose.ComposeController
import ksm.navigation.compose.wrapper.ComposeWrapper
import ksm.navigation.compose.interceptor.ComposeInterceptor
import ksm.navigation.compose.interceptor.plus
import ksm.navigation.compose.wrapper.toInterceptor
import ksm.navigation.serialization.BaseSerializationStore
import ksm.plugin.Plugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.lifecycle.addLifecycleInterceptor
import ksm.plugin.lifecycle.interceptor.LifecycleInterceptor

public class ComposePlugin(
    private val store: BaseSerializationStore.String,
    wrapper: ComposeWrapper? = null
) : Plugin {
    override val key: Companion = ComposePlugin

    private val interceptor = wrapper?.toInterceptor()

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
            return context + ComposeEntry(interceptor)
        }
    }

    private inner class Lifecycle : LifecycleInterceptor {
        override fun onResume(context: StateContext) {
            currentContext.value = context
        }
    }

    public fun addComposeInterceptor(
        context: StateContext,
        interceptor: ComposeInterceptor
    ) {
        context.require(ComposeEntry).interceptor += interceptor
    }

    public fun setContent(
        context: StateContext,
        content: @Composable ComposeController.() -> Unit
    ) {
        val entry = context.require(ComposeEntry)
        val interceptor = entry.interceptor
        val base = ComposeContent(content)
        entry.content = interceptor?.intercept(base) ?: base
    }

    public fun content(context: StateContext): ComposeContent? {
        return context.require(ComposeEntry).content
    }

    public companion object : StateContext.Key<ComposePlugin>
}
