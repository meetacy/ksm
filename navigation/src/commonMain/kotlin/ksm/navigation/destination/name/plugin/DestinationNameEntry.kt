package ksm.navigation.destination.name.plugin

import ksm.context.StateContext

internal class DestinationNameEntry : StateContext.Element {
    override val key = DestinationNameEntry

    var name: String? = null

    companion object : StateContext.Key<DestinationNameEntry>
}
