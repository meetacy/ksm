package ksm.navigation.destination.name

import ksm.context.StateContext
import ksm.navigation.destination.name.plugin.DestinationNamePlugin
import ksm.plugin.plugin

public fun StateContext.setName(name: String) {
    plugin(DestinationNamePlugin).setName(
        context = this,
        name = name
    )
}

public val StateContext.name: String get() {
    return destinationNameOrNull ?: error("Current state has no name")
}

public val StateContext.destinationNameOrNull: String? get() {
    return plugin(DestinationNamePlugin).stateName(context = this)
}
