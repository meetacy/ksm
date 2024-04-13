package ksm.stack

import ksm.context.StateContext
import ksm.plugin.ifPlugin
import ksm.serialization.plugin.BaseSerializationPlugin
import ksm.stack.plugin.StateStackPlugin

public val StateContext.previousContext: StateContext
    get() = previousContextOrNull ?: error("Cannot get previous context")

public val StateContext.previousContextOrNull: StateContext?
    get() = this[StateStackPlugin]?.previousContextOrNull(context = this)

public val StateContext.nextContext: StateContext
    get() = nextContextOrNull ?: error("Cannot get next context")

public val StateContext.nextContextOrNull: StateContext?
    get() = this[StateStackPlugin]?.nextContextOrNull(context = this)

public val StateContext.hasNextContext: Boolean
    get() = nextContextOrNull != null

public val StateContext.lastContext: StateContext
    get() = lastContextOrNull ?: error("There is no child context")

public val StateContext.lastContextOrNull: StateContext? get() {
    var context: StateContext? = null
    while (hasNextContext) {
        context = nextContext
    }
    return context
}

public val StateContext.lastContextOrThis: StateContext
    get() = lastContextOrNull ?: this
