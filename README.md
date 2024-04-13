# Kotlin State Machine

## Overall Idea

This library tries to unify approach of different implications of 
state machines. For now, considered cases to support are:

- Multiplatform Navigation (With convenient native wrappers)
- Native Navigation for android/iOS (Without redundant entities needed for multiplatform)
- FSM for telegram bots

Even though FSM for telegram bots and Navigation seems to be completely 
different things they actually do share lots of common things such as:

- Coroutines Integration (both need to start/cancel scope when start/finish state and structured concurrency when state starts child state)
- Serialization Integration (both need to save entries when state is resumed = 
either the state was just created or it's child was finished and the state become top state)
- Stack Integration (both have the same stack idea which is also shared between them)

## Plugins

All functionality of StateController is extended using Plugins. Plugin is just a simple interface with
the only method 'install(context: Context): Context' which creates a new context with plugin
installed in it.

There are some core principles and idioms of writing plugins.


// TODO:

- plugin and it's internal things under 'plugin' folder
- public apis under feature folder
- you should NEVER append elements to context except for context interceptors (otherwise onResume will work incorrect)

## WIP

Some WIP docs are under [docs folder](docs).

## Example

### Telegram Bots

<details>
  <summary>Example</summary>

```kotlin
package ksm.ktgbotapi.example

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.types.buttons.replyKeyboard
import dev.inmo.tgbotapi.types.buttons.SimpleKeyboardButton
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.update.MessageUpdate
import kotlinx.coroutines.flow.Flow
import ksm.context.finish
import ksm.finish
import ksm.kotlinx.serialization.plugin.KotlinxSerializationPlugin
import ksm.ktgbotapi.*
import ksm.ktgbotapi.match.command
import ksm.ktgbotapi.match.exact
import ksm.state.builder.StateRouteScope
import ksm.state.builder.states
import ksm.state.launch
import ksm.state.name.named

const val MAIN_STATE = "MainState"
const val MAIN_MENU_STATE = "MainMenuState"
const val STATE_A = "MainMenuState"
const val STATE_B = "MainMenuState"

val MessageUpdate.peerKey: TelegramPeerKey get() {
    val message = data as PrivateContentMessage<*>
    val id = message.chat.id
    return TelegramPeerKey(id.chatId.long.toString())
}

suspend fun start(
    bot: TelegramBot,
    updates: Flow<MessageUpdate>
) {
    val fsm = TelegramBotStateMachine {
        install(KotlinxSerializationPlugin())

        states {
            main()
            mainMenu()
            stateA()
            stateB()
        }
    }

    fsm.start(
        startStateName = MAIN_STATE,
        telegramBot = bot,
        updates = updates,
        key = MessageUpdate::peerKey
    )
}

fun StateRouteScope.main() = named(MAIN_STATE) {
    execute {
        sendMessage(
            text = "Hello, ${user.firstName}! Choose an option using buttons below:",
            replyMarkup = replyKeyboard(oneTimeKeyboard = true) {
                +SimpleKeyboardButton(text = "Launch StateA")
                +SimpleKeyboardButton(text = "Launch StateB")
            }
        )
        controller.launch(MAIN_MENU_STATE)
    }
}

fun StateRouteScope.mainMenu() = named(MAIN_MENU_STATE) {
    execute {
        matchMessage {
            exact("Launch StateA") { controller.launch(STATE_A) }
            exact("Launch StateB") { controller.launch(STATE_B) }
            command("cancel") { controller.finish() }
        }
    }
}

fun StateRouteScope.stateA() = named(STATE_A) {
    execute { sendMessage("StateA!") }
}

fun StateRouteScope.stateB() = named(STATE_B) {
    execute { sendMessage("StateB!") }
}
```

</details>

### Compose

<details>
  <summary>Example</summary>

```kotlin
package ksm.compose.example

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import app.meetacy.di.DI
import ksm.mdi.di
import ksm.mdi.plugin.DIPlugin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ksm.compose.Content
import ksm.compose.example.MainViewModel.Action
import ksm.compose.host.StateHost
import ksm.compose.rememberStateController
import ksm.kotlinx.serialization.plugin.KotlinxSerializationPlugin
import ksm.state.builder.StateRouteScope
import ksm.state.builder.states
import ksm.state.data.receive
import ksm.state.launch
import ksm.state.name.named

class MainViewModel {
    val actions: Flow<Action> = emptyFlow()

    sealed interface Action {
        data object RouteDetails : Action
    }
}

data class DetailsParameters(val info: String)

const val MAIN_STATE = "MainState"
const val DETAILS_STATE = "DetailsState"

@Composable
fun AppContent(di: DI) {
    val controller = rememberStateController {
        install(KotlinxSerializationPlugin())
        install(DIPlugin(di))

        states {
            main()
            details()
        }
    }

    StateHost(
        controller = controller,
        startStateName = MAIN_STATE
    )
}

fun StateRouteScope.main() = named(MAIN_STATE) {
    Content {
        val viewModel: MainViewModel = controller.di.viewModel()

        LaunchedEffect(viewModel) {
            viewModel.actions.collect { action ->
                when (action) {
                    Action.RouteDetails -> controller.launch(
                        name = DETAILS_STATE,
                        data = DetailsParameters(info = "Test")
                    )
                }
            }
        }
    }
}

fun StateRouteScope.details() = named(DETAILS_STATE) {
    Content {
        val parameters: DetailsParameters = controller.receive()

        LaunchedEffect(Unit) {
            println(parameters)
        }
    }
}
```

</details>
