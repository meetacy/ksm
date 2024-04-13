package ksm.mdi

import app.meetacy.di.DI
import ksm.state.builder.StateBuilderScope

public var StateBuilderScope.di: DI
    get() = context.di
    set(value) { context.di = value }
