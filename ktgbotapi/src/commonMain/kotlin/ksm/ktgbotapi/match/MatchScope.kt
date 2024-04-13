package ksm.ktgbotapi.match

import ksm.context.StateContext

public class MatchScope(
    public val context: StateContext
) {
    private var default: () -> Unit = { }

    public fun default(block: () -> Unit) {
        default = block
    }

    private var intercepted: Boolean = false

    public fun intercept() {
        require(!intercepted)
        intercepted = true
    }

    public fun runDefault() {
        if (!intercepted) default()
    }
}
