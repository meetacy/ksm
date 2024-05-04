package ksm.navigation.mdi.interceptor

import app.meetacy.di.DI
import ksm.context.StateContext

public fun interface DIInterceptor {
    public fun intercept(
        context: StateContext,
        base: DI
    ): DI
}

public operator fun DIInterceptor?.plus(other: DIInterceptor): DIInterceptor {
    return CombinedDIInterceptor(
        first = this,
        second = other
    )
}
