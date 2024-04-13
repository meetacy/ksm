package ksm.navigation.mdi

import app.meetacy.di.DI
import ksm.navigation.destination.builder.DestinationBuilderScope

public var DestinationBuilderScope.di: DI
    get() = context.di
    set(value) { context.di = value }
