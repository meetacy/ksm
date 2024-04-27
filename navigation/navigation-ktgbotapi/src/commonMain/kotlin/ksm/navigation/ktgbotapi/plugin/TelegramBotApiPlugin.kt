package ksm.navigation.ktgbotapi.plugin

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import ksm.StateController
import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.navigation.ktgbotapi.TelegramPeerKey
import ksm.plugin.Plugin

public class TelegramBotApiPlugin(
    public val peerKey: TelegramPeerKey,
    public val update: Update,
    public val telegramBot: TelegramBot
) : Plugin {
    override val key: Companion = TelegramBotApiPlugin

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            return context + TelegramBotEntry()
        }
    }

    public fun setExecuteBlock(
        context: StateContext,
        block: suspend StateController.() -> Unit
    ) {
        context.require(TelegramBotEntry).execute = block
    }

    public fun executeBlock(context: StateContext): suspend StateController.() -> Unit {
        return context.require(TelegramBotEntry).execute
            ?: error("Please invoke `execute` in StateBuilder")
    }

    public companion object : StateContext.Key<TelegramBotApiPlugin>
}
