package ksm.navigation.ktgbotapi

import ksm.navigation.destination.DestinationScope
import ksm.navigation.destination.builder.DestinationBuilderScope

public fun DestinationBuilderScope.execute(block: suspend DestinationScope.() -> Unit) {
    context.setExecuteBlock(block)
}
