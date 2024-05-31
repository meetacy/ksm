package ksm.navigation.react.observable

public class ObservableState<T>(value: T) {
    private val observers = mutableSetOf<Observer<T>>()

    public fun addObserver(observer: Observer<T>) {
        observers.add(observer)
        observer.accept(value)
    }

    public fun removeObserver(observer: Observer<T>) {
        observers.remove(observer)
    }

    public var value: T = value
        set(value) {
            if (field == value) return
            field = value
            for (observer in observers) {
                observer.accept(value)
            }
        }
}
