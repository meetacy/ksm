package ksm.navigation.react.observable

import react.StateInstance
import react.useEffect
import react.useState

public fun <T> useState(state: ObservableState<T>): StateInstance<T> {
    val result = useState(state.value)

    val (value, setValue) = result

    useEffect(value) {
        state.value = value
    }

    useEffect {
        val observer = Observer<T>(setValue::invoke)
        state.addObserver(observer)
        cleanup { state.removeObserver(observer) }
    }

    return result
}
