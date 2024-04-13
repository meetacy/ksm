package ksm.navigation.state.name.plugin

import ksm.context.StateContext

internal class StateNameEntry : StateContext.Element {
    override val key = StateNameEntry

    var name: String? = null

    companion object : StateContext.Key<StateNameEntry>
}
