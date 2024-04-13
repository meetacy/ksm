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
import ksm.navigation.state.StateScope
import ksm.navigation.ktgbotapi.match.MatchScope

public val StateScope.update: Update get() {
    return controller.context.update
}

@OptIn(PreviewFeature::class)
public val StateScope.user: User get() {
    return update.sourceUser() ?: error("Cannot get user from $update")
}

@OptIn(PreviewFeature::class)
public val StateScope.chatId: IdChatIdentifier get() {
    return update.sourceChat()?.id ?: error("Cannot get chatId from $update")
}

public val StateScope.telegramBot: TelegramBot get() {
    return controller.context.telegramBot
}

public suspend fun StateScope.sendMessage(
    text: String,
    replyMarkup: KeyboardMarkup? = null
) {
    telegramBot.sendMessage(chatId, text, replyMarkup = replyMarkup)
}

public inline fun StateScope.matchMessage(block: MatchScope.() -> Unit) {
    MatchScope(controller.context).apply(block)
}
