package ksm.navigation.react.observable

public class ObservableState<T>(value: T) {
    private val observers = mutableListOf<Observer<T>>()

    public fun addObserver(observer: Observer<T>) {
        observers.add(observer)
        observer.accept(value)
    }

    public var value: T = value
        set(value) {
            field = value
            for (observer in observers) {
                observer.accept(value)
            }
        }
}
