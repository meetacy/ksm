package ksm.navigation.result.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.context.finish
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
import ksm.navigation.destination.navigate
import ksm.navigation.destination.parameters.receiveParameter
import ksm.navigation.destination.parameters.setParameter
import ksm.plugin.Plugin
import ksm.navigation.result.NavigatorForResult
import ksm.navigation.result.NavigationResultHandler
import ksm.navigation.stack.previousContext
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
            override fun navigate(value: TypedValue<T>) {
                context.navigate(name, value) { child ->
                    child.setParameter(RESULT_ID_KEY, resultId.int)
                }
            }
        }
    }
}
