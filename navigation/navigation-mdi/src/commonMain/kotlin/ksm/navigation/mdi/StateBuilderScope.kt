package ksm.navigation.mdi

import app.meetacy.di.DI
import ksm.navigation.route.StateBuilderScope

public var StateBuilderScope.di: DI
    get() = context.di
    set(value) { context.di = value }
