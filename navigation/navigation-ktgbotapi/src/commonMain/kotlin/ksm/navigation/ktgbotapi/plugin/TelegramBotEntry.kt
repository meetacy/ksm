package ksm.navigation.ktgbotapi.plugin

import ksm.context.StateContext
import ksm.navigation.state.StateScope

internal class TelegramBotEntry : StateContext.Element {
    override val key = TelegramBotEntry

    var execute: (suspend StateScope.() -> Unit)? = null

    companion object : StateContext.Key<TelegramBotEntry>
}
