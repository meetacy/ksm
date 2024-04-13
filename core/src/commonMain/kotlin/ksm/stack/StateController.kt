package ksm.stack

import ksm.StateController

public val StateController.previous: StateController get() {
    return StateController(context.previousContext)
}

public val StateController.root: StateController get() {
    return StateController(context.rootContext)
}
