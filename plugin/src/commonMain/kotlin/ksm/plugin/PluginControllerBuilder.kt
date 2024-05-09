package ksm.plugin

import ksm.annotation.LibraryApi
import ksm.plugin.configuration.installConfiguration
import ksm.plugin.factory.StateControllerFactory
import ksm.plugin.factory.installControllerFactory
import ksm.plugin.finish.installFinishOnce
import ksm.plugin.lifecycle.installLifecycle

@LibraryApi
public inline fun PluginController.Builder.pluginRuntime(
    controllerFactory: StateControllerFactory,
    enableConfiguration: Boolean = true,
    enableLifecycle: Boolean = true,
    enableFinishOnce: Boolean = true
) {
    if (enableConfiguration) installConfiguration()
    if (enableLifecycle) installLifecycle()
    if (enableFinishOnce) installFinishOnce()
    installControllerFactory(controllerFactory)
}
