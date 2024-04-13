@file:OptIn(InstallStackPlugin::class)

package ksm.navigation.stack

import ksm.context.StateContext
import ksm.navigation.annotation.InstallStackPlugin
import ksm.navigation.stack.plugin.StackPlugin

public val StateContext.previousContext: StateContext
    get() = previousContextOrNull ?: error("Cannot get previous context")

public val StateContext.previousContextOrNull: StateContext?
    get() = this[StackPlugin]?.previousContextOrNull(context = this)

public val StateContext.hasPreviousContext: Boolean get() {
    return previousContextOrNull != null
}

public val StateContext.nextContext: StateContext
    get() = nextContextOrNull ?: error("Cannot get next context")

public val StateContext.nextContextOrNull: StateContext?
    get() = this[StackPlugin]?.nextContextOrNull(context = this)

public val StateContext.lastContext: StateContext get() {
    var context: StateContext = this
    while (context.hasNextContext) {
        context = nextContext
    }
    return context
}

public val StateContext.hasNextContext: Boolean
    get() = nextContextOrNull != null

public val StateContext.rootContext: StateContext get() {
    var context: StateContext = this
    while (context.hasPreviousContext) {
        context = previousContext
    }
    return context
}
