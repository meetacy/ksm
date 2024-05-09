package ksm.viewmodel.exceptions.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.viewmodel.exceptions.ExceptionHandler
import ksm.plugin.Plugin
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor
import ksm.plugin.configuration.interceptor.addConfigurationInterceptor

public object ExceptionHandlerPlugin : Plugin.Singleton<ExceptionHandlerPlugin> {

    @MutateContext
    override fun install(context: StateContext): StateContext {
        val entry = ExceptionHandlerEntry()
        context.addConfigurationInterceptor(Configuration(entry))
        return context + entry
    }

    private class Configuration(
        val entry: ExceptionHandlerEntry
    ) : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            return context + ExceptionHandlerEntry(entry)
        }
    }

    public fun setExceptionHandler(
        context: StateContext,
        handler: ExceptionHandler
    ) {
        context.require(ExceptionHandlerEntry).handler = handler
    }

    public fun exceptionHandler(context: StateContext): ExceptionHandler? {
        return context.require(ExceptionHandlerEntry).handler
    }
}
