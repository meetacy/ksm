package ksm.coroutines

import kotlinx.coroutines.CoroutineScope
import ksm.StateController

public val StateController.coroutineScope: CoroutineScope
    get() = context.coroutineScope
