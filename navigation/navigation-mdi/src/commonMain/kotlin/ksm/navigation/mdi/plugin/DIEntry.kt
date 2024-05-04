package ksm.navigation.mdi.plugin

import app.meetacy.di.DI
import ksm.context.StateContext
import ksm.navigation.mdi.interceptor.DIInterceptor

internal class DIEntry : StateContext.Element {
    override val key = DIEntry

    var interceptor: DIInterceptor? = null
    var di: DI? = null

    companion object : StateContext.Key<DIEntry>
}
