package ksm.navigation.ktgbotapi

import ksm.navigation.state.StateScope
import ksm.navigation.state.builder.StateBuilderScope

public fun StateBuilderScope.execute(block: suspend StateScope.() -> Unit) {
    context.setExecuteBlock(block)
}
