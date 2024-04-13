package ksm.ktgbotapi.plugin

import ksm.context.StateContext
import ksm.state.StateScope

internal class TelegramBotEntry : StateContext.Element {
    override val key = TelegramBotEntry

    var execute: (suspend StateScope.() -> Unit)? = null

    companion object : StateContext.Key<TelegramBotEntry>
}
