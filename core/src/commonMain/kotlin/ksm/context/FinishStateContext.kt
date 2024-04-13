package ksm.context

import ksm.finish.once.plugin.FinishOncePlugin
import ksm.stack.previousContextOrNull
import ksm.lifecycle.plugin.LifecyclePlugin

public fun StateContext.finish(): StateContext {
    this[FinishOncePlugin]?.finish(context = this)
    this[LifecyclePlugin]?.onPause(context = this)
    this[LifecyclePlugin]?.onFinish(context = this)
    val previousContext = previousContextOrNull ?: return this
    previousContext[LifecyclePlugin]?.onResume(context = previousContext)
    return this
}
