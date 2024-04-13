package ksm.ktgbotapi

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.flow.Flow
import ksm.StateController
import ksm.builder.StateControllerBuilder
import ksm.createRawStateController
import ksm.ktgbotapi.plugin.TelegramBotApiPlugin
import ksm.serialization.restore
import ksm.stack.lastContext
import ksm.stack.lastContextOrNull
import ksm.stack.nextContext
import ksm.stack.nextContextOrNull
import ksm.state.StateScope
import ksm.state.launch

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
            if (controller.context.lastContextOrNull == null) {
                controller.launch(startStateName)
            }
            // todo: это дефолтный интерцептор, надо дать возможность
            //  добавлять свои интерцепторы на апдейты после восстановления состояния
            //  -
            //  Новых абстракций можно не придумывать, а в случае, когда необходимо блокировать
            //  выполнение цепочки, можно сделать декоратор
            val lastContext = controller.context.lastContext
            val lastController = StateController(lastContext)
            val stateScope = StateScope(lastController)
            lastContext.executeBlock.invoke(stateScope)
        }
    }

    private fun createStateController(
        key: TelegramPeerKey,
        update: Update,
        telegramBot: TelegramBot
    ): StateController {
        return createRawStateController {
            install(TelegramBotApiPlugin(key, update, telegramBot))
            builder()
        }.apply {
            context.restore()
        }
    }
}
