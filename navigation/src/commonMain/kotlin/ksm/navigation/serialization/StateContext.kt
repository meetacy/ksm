package ksm.navigation.serialization

import ksm.context.StateContext
import ksm.plugin.plugin
import ksm.navigation.serialization.plugin.BaseSerializationPlugin

public fun StateContext.restore() {
    plugin(BaseSerializationPlugin).restore(context = this)
}
