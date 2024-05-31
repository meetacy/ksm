package ksm.navigation.react.observable

import react.StateInstance
import react.useEffect
import react.useEffectOnce
import react.useState
import web.cssom.array

public fun <T> useState(state: ObservableState<T>): StateInstance<T> {
    val result = useState(state.value)

    val (value, setValue) = result
    state.value = value

    useEffectOnce {
        val observer = Observer<T>(setValue::invoke)
        state.addObserver(observer)
        cleanup { state.removeObserver(observer) }
    }

    return result
}
