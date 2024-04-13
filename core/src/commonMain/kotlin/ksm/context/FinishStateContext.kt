package ksm.context

import ksm.finish.once.plugin.FinishOncePlugin
import ksm.lifecycle.plugin.LifecyclePlugin

public fun StateContext.finish(): StateContext {
    this[FinishOncePlugin]?.finish(context = this)
    this[LifecyclePlugin]?.onPause(context = this)
    this[LifecyclePlugin]?.onFinish(context = this)
    return this
}
