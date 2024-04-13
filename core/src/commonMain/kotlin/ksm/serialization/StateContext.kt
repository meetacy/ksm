package ksm.serialization

import ksm.context.StateContext
import ksm.plugin.plugin
import ksm.serialization.plugin.BaseSerializationPlugin

public fun StateContext.restore() {
    plugin(BaseSerializationPlugin).restore(context = this)
}
