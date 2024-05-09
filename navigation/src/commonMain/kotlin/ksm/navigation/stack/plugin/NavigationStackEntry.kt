package ksm.navigation.stack.plugin

import ksm.context.StateContext
import ksm.finish

internal class NavigationStackEntry(
    private val previousEntry: NavigationStackEntry?
) : StateContext.Element {
    override val key = NavigationStackEntry

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

    companion object : StateContext.Key<NavigationStackEntry>
}
