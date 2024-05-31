package ksm.navigation.route

import ksm.context.StateContext
import ksm.navigation.annotation.StateRouteDSL

@StateRouteDSL
public interface StateBuilderScope {
    public val context: StateContext
}
