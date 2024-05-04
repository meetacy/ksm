package ksm.configuration.interceptor

import ksm.context.StateContext
import ksm.configuration.plugin.ConfigurationPlugin
import ksm.plugin.plugin

public fun StateContext.addConfigurationInterceptor(interceptor: ConfigurationInterceptor) {
    plugin(ConfigurationPlugin).addConfigurationInterceptor(
        context = this,
        interceptor = interceptor
    )
}
