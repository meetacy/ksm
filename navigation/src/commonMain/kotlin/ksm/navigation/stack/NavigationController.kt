package ksm.navigation.stack

import ksm.StateController
import ksm.asStateController

public val StateController.previous: StateController get() {
    return context.previousContext.asStateController()
}

public val StateController.root: StateController get() {
    return context.rootContext.asStateController()
}
