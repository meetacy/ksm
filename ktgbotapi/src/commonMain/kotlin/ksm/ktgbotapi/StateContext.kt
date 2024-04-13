package ksm.ktgbotapi

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.update.MessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import ksm.StateController
import ksm.context.StateContext
import ksm.ktgbotapi.plugin.TelegramBotApiPlugin
import ksm.plugin.plugin
import ksm.state.StateScope

public val StateContext.update: Update get() {
    return plugin(TelegramBotApiPlugin).update
}

public val StateContext.messageUpdate: MessageUpdate get() {
    return update as? MessageUpdate ?: error("Received update is not MessageUpdate")
}

public val StateContext.message: Message get() {
    return messageUpdate.data
}

public val StateContext.telegramBot: TelegramBot get() {
    return plugin(TelegramBotApiPlugin).telegramBot
}

internal val StateContext.executeBlock: suspend StateScope.() -> Unit get() {
    return plugin(TelegramBotApiPlugin).executeBlock(context = this)
}

public fun StateContext.setExecuteBlock(block: suspend StateScope.() -> Unit) {
    plugin(TelegramBotApiPlugin).setExecuteBlock(
        context = this,
        block = block
    )
}
