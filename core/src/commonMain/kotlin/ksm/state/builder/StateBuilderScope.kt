package ksm.state.builder

import ksm.annotation.StateBuilderDSL
import ksm.context.StateContext

@StateBuilderDSL
public class StateBuilderScope(public val context: StateContext)
