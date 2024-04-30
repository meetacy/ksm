package ksm.navigation.mdi

import app.meetacy.di.DI
import app.meetacy.di.builder.DIBuilder
import app.meetacy.di.builder.di
import ksm.builder.StateControllerBuilder
import ksm.navigation.mdi.plugin.DIPlugin

public inline fun StateControllerBuilder.installDI(
    di: DI = di {},
    checkDependencies: Boolean = true,
    block: DIBuilder.() -> Unit = {}
) {
    val root = di(di, checkDependencies, block)
    install(DIPlugin(root))
}
