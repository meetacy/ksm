package ksm.navigation.result.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.finish
import ksm.navigation.state.navigate
import ksm.navigation.state.parameters.receiveParameter
import ksm.navigation.state.parameters.setParameter
import ksm.plugin.Plugin
import ksm.navigation.result.NavigatorForResult
import ksm.navigation.result.NavigationResultHandler
import ksm.navigation.stack.previousContext
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor
import ksm.plugin.lifecycle.addLifecycleInterceptor
import ksm.plugin.lifecycle.interceptor.LifecycleInterceptor
import ksm.typed.TypedValue

public object NavigationResultPlugin : Plugin.Singleton<NavigationResultPlugin> {
    private const val RESULT_ID_KEY = "ksm.result.plugin.RESULT_ID"

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            val entry = NavigationResultEntry()
            context.addLifecycleInterceptor(Lifecycle(entry))
            return context + entry
        }
    }

    private class Lifecycle(val entry: NavigationResultEntry) : LifecycleInterceptor {
        override fun onResume(context: StateContext) {
            entry.onResume()
        }
    }

    public fun finishWithResult(
        context: StateContext,
        result: Any?
    ) {
        val resultId: Int = context.receiveParameter(RESULT_ID_KEY)
            ?: error("Cannot finish with result state that was not launched with LauncherForResult")

        context.previousContext
            .require(NavigationResultEntry)
            .setResult(NavigationResultId(resultId), result)

        context.finish()
    }

    public fun <T> registerNavigator(
        context: StateContext,
        name: String,
        handler: NavigationResultHandler<*>
    ): NavigatorForResult<T> {
        val resultId = context
            .require(NavigationResultEntry)
            .registerNavigator(handler)

        return object : NavigatorForResult<T> {
            override fun navigate(value: TypedValue) {
                context.navigate(name, value) { child ->
                    child.setParameter(RESULT_ID_KEY, resultId.int)
                }
            }
        }
    }
}
