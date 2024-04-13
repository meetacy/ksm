# Exposing Plugin API

Class or object implementing `Plugin` interface must have all public methods
required to interact with the feature. A good example of such plugin is
`CoroutinesPlugin`:

```kotlin
object CoroutinesPlugin : Plugin {
    override fun install(context: StateContext): StateContext { ... }
    
    fun coroutineContext(context: StateContext): CoroutineContext {
        return context.require(CoroutineContextKey).coroutineContext
    }
}
```

All the keys of the plugin should be internal or even private, you should not share
keys directly but rather provide convenient extensions on top of StateContext.

Continuing with `CoroutinesPlugin`, the extension to get coroutineContext looks like
this:

```kotlin
val StateContext.coroutineContext: CoroutineContext get() {
    return plugin(CoroutinesPlugin).coroutineContext(context = this)
}
```

Here you can see why you should not share Key:

If there is no plugin
installed the Key will be absent and any operation will fail saying
'Cannot find element with key $key'. And the name of the key can
be literally anything and user of the plugin probably knows
no more than the plugin name.

When you get plugin using `plugin` function, it first checks if the
plugin itself is installed. And if it isn't, it throws an exception
saying 'Plugin $name is not installed' which is ultimately more clear.

Additionally, that way we know that when 'Cannot find element with key $key'
exception in thrown, it's due to the bug in the plugin and not user's fault.
