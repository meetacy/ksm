package ksm.navigation.route.plugin

import ksm.context.StateContext

internal class StateRouteStateController : StateContext.Element {
    override val key = StateRouteStateController

    private var block: ((StateContext) -> Unit)? = null

    fun setBlock(block: (StateContext) -> Unit) {
        this.block = block
    }

    fun block(): (StateContext) -> Unit {
        return block ?: error("Please call `states` in StateControllerBuilder")
    }

    companion object : StateContext.Key<StateRouteStateController>
}
