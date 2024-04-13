package ksm.coroutines

import kotlinx.coroutines.CoroutineScope
import ksm.StateController

public val StateController.scope: CoroutineScope
    get() = context.scope
