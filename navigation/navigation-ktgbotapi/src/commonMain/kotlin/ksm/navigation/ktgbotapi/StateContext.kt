package ksm.navigation.ktgbotapi

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.update.MessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import ksm.StateController
import ksm.context.StateContext
import ksm.navigation.ktgbotapi.plugin.TelegramBotApiPlugin
import ksm.plugin.plugin

public val StateContext.update: Update get() {
    return plugin(TelegramBotApiPlugin).update
}

// todo: правило для экстеншенов – они должны работать либо всегда, либо никогда
//  нельзя сделать экстеншен, который будет падать, если пришёл один тг запрос и
//  который будет работать, если пришёл другой.
//  это надо решать каким-то другим образом
//  Для этого мб потребуется сделать states отдельным плагином
//
public val StateContext.messageUpdate: MessageUpdate get() {
    return update as? MessageUpdate ?: error("Received update is not MessageUpdate")
}

public val StateContext.message: Message get() {
    return messageUpdate.data
}

public val StateContext.telegramBot: TelegramBot get() {
    return plugin(TelegramBotApiPlugin).telegramBot
}

internal val StateContext.executeBlock: suspend StateController.() -> Unit get() {
    return plugin(TelegramBotApiPlugin).executeBlock(context = this)
}

public fun StateContext.setExecuteBlock(block: suspend StateController.() -> Unit) {
    plugin(TelegramBotApiPlugin).setExecuteBlock(
        context = this,
        block = block
    )
}
