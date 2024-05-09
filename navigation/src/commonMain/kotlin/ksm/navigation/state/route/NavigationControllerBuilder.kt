package ksm.navigation.state.route

import ksm.annotation.LibraryApi
import ksm.navigation.NavigationController
import ksm.navigation.state.route.plugin.StateRoutePlugin
import ksm.plugin.install

@OptIn(LibraryApi::class)
public fun NavigationController.Builder.installStateRoute() {
    install(StateRoutePlugin)
}
