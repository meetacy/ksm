package ksm

import ksm.context.StateContext
import ksm.plugin.finish.plugin.FinishOncePlugin
import ksm.plugin.lifecycle.plugin.LifecyclePlugin

public fun StateContext.finish() {
    this[FinishOncePlugin]?.finish(context = this)
    this[LifecyclePlugin]?.onPause(context = this)
    this[LifecyclePlugin]?.onFinish(context = this)
}
