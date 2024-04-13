package ksm.navigation.destination.name

import ksm.navigation.destination.builder.DestinationBuilderScope
import ksm.navigation.destination.builder.DestinationRouteScope

public inline fun DestinationRouteScope.named(
    string: String,
    block: DestinationBuilderScope.() -> Unit
) {
    intercept(
        predicate = { context.destinationNameOrNull == string },
        block = block
    )
}
