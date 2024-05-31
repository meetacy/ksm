package ksm.navigation.react.plugin

import ksm.context.StateContext

internal class ReactEntry : StateContext.Element {
    override val key = ReactEntry

    var component: ReactComponent? = null

    companion object : StateContext.Key<ReactEntry>
}
