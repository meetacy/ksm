package ksm.navigation.mdi

import app.meetacy.di.DI
import ksm.navigation.mdi.plugin.DIPlugin
import ksm.context.StateContext
import ksm.plugin.plugin

public var StateContext.di: DI
    get() = plugin(DIPlugin).di(context = this)
    set(value) = plugin(DIPlugin).setDI(context = this, di = value)