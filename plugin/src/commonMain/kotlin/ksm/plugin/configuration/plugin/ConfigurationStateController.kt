package ksm.plugin.configuration.plugin

import ksm.context.StateContext
import ksm.plugin.configuration.interceptor.ConfigurationInterceptor

internal class ConfigurationStateController : StateContext.Element {
    override val key = ConfigurationStateController

    var interceptor: ConfigurationInterceptor? = null

    companion object : StateContext.Key<ConfigurationStateController>
}
