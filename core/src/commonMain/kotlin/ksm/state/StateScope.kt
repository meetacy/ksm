package ksm.state

import ksm.StateController
import ksm.annotation.StateBuilderDSL

@StateBuilderDSL
public class StateScope(public val controller: StateController)
