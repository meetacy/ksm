package ksm.stack.plugin

import ksm.context.StateContext
import ksm.context.finish
import ksm.context.get
import ksm.state.name.stateName

internal class StateStackEntry(
    private val previousEntry: StateStackEntry?
) : StateContext.Element {
    override val key = StateStackEntry

    val previousContext: StateContext? get() = previousEntry?.context

    private lateinit var context: StateContext

    var nextContext: StateContext? = null
        private set

    fun attachContext(context: StateContext) {
        this.context = context
        previousEntry?.nextContext = context
    }

    fun onFinish() {
        previousEntry?.nextContext = null
        nextContext?.finish()
    }

    companion object : StateContext.Key<StateStackEntry>
}
