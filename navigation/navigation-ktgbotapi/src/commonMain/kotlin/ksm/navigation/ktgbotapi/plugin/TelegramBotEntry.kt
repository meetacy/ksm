package ksm.navigation.ktgbotapi.plugin

import ksm.context.StateContext
import ksm.navigation.destination.DestinationScope

internal class TelegramBotEntry : StateContext.Element {
    override val key = TelegramBotEntry

    var execute: (suspend DestinationScope.() -> Unit)? = null

    companion object : StateContext.Key<TelegramBotEntry>
}
