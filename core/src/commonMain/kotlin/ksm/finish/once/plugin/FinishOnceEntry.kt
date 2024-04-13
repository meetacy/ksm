package ksm.finish.once.plugin

import ksm.context.StateContext

internal class FinishOnceEntry : StateContext.Element {
    override val key = FinishOncePlugin

    private var isFinished: Boolean = false

    fun checkCanCreate() {
        if (isFinished) error("Current state was already finished, cannot create a new one")
    }

    fun finish() {
        if (isFinished) error("Current state was already finished, cannot finish it twice")
        isFinished = true
    }

    companion object : StateContext.Key<FinishOnceEntry>
}
