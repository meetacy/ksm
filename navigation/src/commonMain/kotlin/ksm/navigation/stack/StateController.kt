package ksm.navigation.stack

import ksm.StateController
import ksm.asStateController

public val StateController.previousController: StateController get() {
    return context.previousContext.asStateController()
}

public val StateController.rootController: StateController get() {
    return context.rootContext.asStateController()
}
