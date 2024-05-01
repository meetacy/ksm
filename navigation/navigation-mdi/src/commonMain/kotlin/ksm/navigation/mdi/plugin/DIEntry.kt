package ksm.navigation.mdi.plugin

import app.meetacy.di.DI
import ksm.context.StateContext

internal class DIEntry(base: DI? = null) : StateContext.Element {
    override val key = DIEntry

    var di: DI? = base

    companion object : StateContext.Key<DIEntry>
}
