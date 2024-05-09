package ksm.navigation.state.parameters

import ksm.annotation.LibraryApi
import ksm.navigation.NavigationController
import ksm.navigation.state.parameters.plugin.StateParametersPlugin
import ksm.plugin.install

@OptIn(LibraryApi::class)
public fun NavigationController.Builder.installStateParameters() {
    install(StateParametersPlugin)
}
