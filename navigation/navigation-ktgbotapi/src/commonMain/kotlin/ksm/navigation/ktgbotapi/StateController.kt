package ksm.navigation.ktgbotapi

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.sendMessage
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceUser
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.utils.PreviewFeature
import ksm.StateController
import ksm.navigation.ktgbotapi.match.MatchScope

public val StateController.update: Update get() {
    return context.update
}

@OptIn(PreviewFeature::class)
public val StateController.user: User get() {
    return update.sourceUser() ?: error("Cannot get user from $update")
}

@OptIn(PreviewFeature::class)
public val StateController.chatId: IdChatIdentifier get() {
    return update.sourceChat()?.id ?: error("Cannot get chatId from $update")
}

public val StateController.telegramBot: TelegramBot get() {
    return context.telegramBot
}

public suspend fun StateController.sendMessage(
    text: String,
    replyMarkup: KeyboardMarkup? = null
) {
    telegramBot.sendMessage(chatId, text, replyMarkup = replyMarkup)
}

public inline fun StateController.matchMessage(block: MatchScope.() -> Unit) {
    MatchScope(context).apply(block)
}
