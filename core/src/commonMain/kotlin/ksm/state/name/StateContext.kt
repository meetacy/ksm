package ksm.state.name

import ksm.context.StateContext
import ksm.plugin.plugin
import ksm.state.name.plugin.StateNamePlugin

public fun StateContext.setStateName(name: String) {
    plugin(StateNamePlugin).setName(
        context = this,
        name = name
    )
}

public val StateContext.stateName: String get() {
    return stateNameOrNull ?: error("Current state has no name")
}

public val StateContext.stateNameOrNull: String? get() {
    return plugin(StateNamePlugin).stateName(context = this)
}
