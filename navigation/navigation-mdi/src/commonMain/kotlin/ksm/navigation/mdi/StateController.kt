package ksm.navigation.mdi

import app.meetacy.di.DI
import ksm.StateController

public val StateController.di: DI get() {
    return context.di
}
