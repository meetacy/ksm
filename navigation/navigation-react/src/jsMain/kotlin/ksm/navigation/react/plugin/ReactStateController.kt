package ksm.navigation.react.plugin

import ksm.context.StateContext
import ksm.navigation.react.observable.ObservableState

internal class ReactStateController : StateContext.Element {
    override val key = ReactStateController

    val currentContext = ObservableState<StateContext?>(value = null)

    companion object : StateContext.Key<ReactStateController>
}
