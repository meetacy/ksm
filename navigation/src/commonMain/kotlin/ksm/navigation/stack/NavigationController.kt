package ksm.navigation.stack

import ksm.navigation.NavigationController
import ksm.plugin.factory.asController

public inline val <reified T : NavigationController> T.previousController: T get() {
    return context.previousContext.asController()
}

public inline val <reified T : NavigationController> T.rootController: T get() {
    return context.rootContext.asController()
}
