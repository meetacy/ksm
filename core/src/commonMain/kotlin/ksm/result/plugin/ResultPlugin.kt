package ksm.result.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.context.finish
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
import ksm.plugin.Plugin
import ksm.result.LauncherForResult
import ksm.result.ResultHandler
import ksm.stack.previousContext
import ksm.state.launchChildContext
import ksm.state.parameters.receiveStateParameter
import ksm.state.parameters.setStateParameter
import ksm.typed.TypedValue

public object ResultPlugin : Plugin.Singleton<ResultPlugin> {
    private const val RESULT_ID_KEY = "ksm.result.plugin.RESULT_ID"

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            val entry = ResultEntry()
            context.addLifecycleInterceptor(Lifecycle(entry))
            return context + entry
        }
    }

    private class Lifecycle(val entry: ResultEntry) : LifecycleInterceptor {
        override fun onResume(context: StateContext) {
            entry.onResume()
        }
    }

    public fun finishWithResult(
        context: StateContext,
        result: Any?
    ) {
        val resultId: Int = context.receiveStateParameter(RESULT_ID_KEY)
            ?: error("Cannot finish with result state that was not launched with LauncherForResult")

        context.previousContext
            .require(ResultEntry)
            .setResult(ResultId(resultId), result)

        context.finish()
    }

    public fun <T> registerLauncher(
        context: StateContext,
        name: String,
        handler: ResultHandler<*>
    ): LauncherForResult<T> {
        val resultId = context
            .require(ResultEntry)
            .registerLauncher(handler)

        return object : LauncherForResult<T> {
            override fun launch(value: TypedValue<T>) {
                context.launchChildContext(name, value) { child ->
                    child.setStateParameter(RESULT_ID_KEY, resultId.int)
                }
            }
        }
    }
}
