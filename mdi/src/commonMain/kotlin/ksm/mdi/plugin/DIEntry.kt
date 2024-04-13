package ksm.mdi.plugin

import app.meetacy.di.DI
import ksm.context.StateContext

internal class DIEntry(private val root: DI) : StateContext.Element {
    override val key = DIEntry

    private var di: DI? = root

    fun setDI(di: DI?) {
        if (di == null) {
            this.di = null
            return
        }
        this.di = root + di
    }

    fun getDI() = di

    companion object : StateContext.Key<DIEntry>
}
