package ksm.navigation.mdi

import app.meetacy.di.DI
import app.meetacy.di.builder.DIBuilder
import app.meetacy.di.builder.di
import ksm.annotation.LibraryApi
import ksm.navigation.mdi.plugin.DIPlugin
import ksm.plugin.PluginController
import ksm.plugin.install

@OptIn(LibraryApi::class)
public inline fun PluginController.Builder.installDI(
    di: DI = di {},
    checkDependencies: Boolean = true,
    block: DIBuilder.() -> Unit = {}
) {
    val root = di(di, checkDependencies, block)
    install(DIPlugin(root))
}
