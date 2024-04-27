package ksm.navigation.ktgbotapi

import ksm.StateController
import ksm.navigation.state.builder.StateBuilderScope

public fun StateBuilderScope.execute(block: suspend StateController.() -> Unit) {
    context.setExecuteBlock(block)
}
