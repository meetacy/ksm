package ksm.navigation.ktgbotapi

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.flow.Flow
import ksm.StateController
import ksm.annotation.LibraryApi
import ksm.navigation.state.navigate
import ksm.navigation.ktgbotapi.plugin.TelegramBotApiPlugin
import ksm.navigation.navigationRuntime
import ksm.navigation.serialization.restore
import ksm.navigation.stack.hasNextContext
import ksm.navigation.stack.lastContext

public class TelegramBotStateMachine(
    private val builder: StateControllerBuilder.() -> Unit
) {
    public suspend fun <T : Update> start(
        startStateName: String,
        telegramBot: TelegramBot,
        updates: Flow<T>,
        key: (T) -> TelegramPeerKey
    ) {
        updates.collect { update ->
            val controller = createStateController(key(update), update, telegramBot)
            if (!controller.context.hasNextContext) {
                controller.navigate(startStateName)
            }
            // todo: это дефолтный интерцептор, надо дать возможность
            //  добавлять свои интерцепторы на апдейты после восстановления состояния
            //  -
            //  Новых абстракций можно не придумывать, а в случае, когда необходимо блокировать
            //  выполнение цепочки, можно сделать декоратор
            val lastContext = controller.context.lastContext
            val lastController = lastContext.asStateController()
            lastContext.executeBlock.invoke(lastController)
        }
    }

    @OptIn(LibraryApi::class)
    private fun createStateController(
        key: TelegramPeerKey,
        update: Update,
        telegramBot: TelegramBot
    ): StateController {
        return navigationRuntime {
            install(TelegramBotApiPlugin(key, update, telegramBot))
            builder()
        }.apply {
            context.restore()
        }
    }
}
