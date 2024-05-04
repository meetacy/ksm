package ksm.navigation.mdi.interceptor

import app.meetacy.di.DI
import ksm.context.StateContext

internal class CombinedDIInterceptor(
    private val first: DIInterceptor?,
    private val second: DIInterceptor
) : DIInterceptor {
    override fun intercept(context: StateContext, base: DI): DI {
        var applied = base
        applied = first?.intercept(context, applied) ?: applied
        applied = second.intercept(context, applied)
        return applied
    }
}
