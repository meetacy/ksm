package ksm.navigation.state.name

import ksm.context.StateContext
import ksm.navigation.state.name.plugin.StateNamePlugin
import ksm.plugin.plugin

public fun StateContext.setName(name: String) {
    plugin(StateNamePlugin).setName(
        context = this,
        name = name
    )
}

public val StateContext.name: String get() {
    return stateNameOrNull ?: error("Current state has no name")
}

public val StateContext.stateNameOrNull: String? get() {
    return plugin(StateNamePlugin).stateName(context = this)
}
