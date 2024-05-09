package ksm.coroutines

import kotlinx.coroutines.CoroutineScope
import ksm.plugin.PluginController

public val PluginController.scope: CoroutineScope
    get() = context.scope
