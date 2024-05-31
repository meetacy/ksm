package ksm.navigation.react.observable

import react.StateInstance
import react.useEffect
import react.useState

public fun <T> useObservableState(state: StateInstance<T>): ObservableState<T> {
    val (value, setValue) = state

    val (result) = useState { ObservableState(value) }
    useEffect(value) { result.value = value }
    result.addObserver { newValue -> setValue(newValue) }

    return result
}

public fun <T> useState(state: ObservableState<T>): StateInstance<T> {
    val result = useState(state.value)

    val (value, setValue) = result
    useEffect(value) { state.value = value }
    state.addObserver { newValue -> setValue(newValue) }

    return result
}
