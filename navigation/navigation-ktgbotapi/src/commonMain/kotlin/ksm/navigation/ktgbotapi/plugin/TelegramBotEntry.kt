package ksm.navigation.ktgbotapi.plugin

import ksm.StateController
import ksm.context.StateContext

internal class TelegramBotEntry : StateContext.Element {
    override val key = TelegramBotEntry

    var execute: (suspend StateController.() -> Unit)? = null

    companion object : StateContext.Key<TelegramBotEntry>
}
