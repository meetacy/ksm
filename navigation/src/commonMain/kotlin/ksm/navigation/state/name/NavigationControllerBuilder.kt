package ksm.navigation.state.name

import ksm.annotation.LibraryApi
import ksm.navigation.NavigationController
import ksm.navigation.state.name.plugin.StateNamePlugin
import ksm.plugin.install

@OptIn(LibraryApi::class)
public fun NavigationController.Builder.installStateName() {
    install(StateNamePlugin)
}
