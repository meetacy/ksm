package ksm.ktgbotapi

import ksm.state.StateScope
import ksm.state.builder.StateBuilderScope

public fun StateBuilderScope.execute(block: suspend StateScope.() -> Unit) {
    context.setExecuteBlock(block)
}
