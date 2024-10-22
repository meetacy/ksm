package ksm.plugin

import ksm.annotation.MutateContext
import ksm.context.StateContext

@MutateContext
public fun StateContext.install(plugin: Plugin): StateContext {
    if (plugin.key in this) {
        error("Plugin ${plugin::class.simpleName} is already installed")
    }
    return plugin.install(context = this) + plugin
}

@Deprecated(
    message = "Use array access syntax instead",
    replaceWith = ReplaceWith(
        expression = "get(key)"
    )
)
public inline fun <reified T : Plugin> StateContext.pluginOrNull(key: StateContext.Key<T>): T? {
    return this[key]
}

public inline fun <reified T : Plugin> StateContext.ifPlugin(
    key: StateContext.Key<T>,
    block: T.() -> Unit
) {
    this[key]?.run(block)
}

public inline fun <reified T : Plugin> StateContext.plugin(key: StateContext.Key<T>): T {
    return this[key] ?: error("Plugin `${T::class.simpleName}` is not installed")
}

public inline fun <reified T : Plugin> StateContext.withPlugin(
    key: StateContext.Key<T>,
    block: T.() -> Unit
) {
    plugin(key).run(block)
}
