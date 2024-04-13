package ksm.exceptions.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.exceptions.ExceptionHandler
import ksm.plugin.Plugin

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
