package ksm.configuration.plugin

import ksm.configuration.interceptor.ConfigurationInterceptor
import ksm.context.StateContext

internal class ConfigurationStateController : StateContext.Element {
    override val key = ConfigurationStateController

    var interceptor: ConfigurationInterceptor? = null

    companion object : StateContext.Key<ConfigurationStateController>
}
