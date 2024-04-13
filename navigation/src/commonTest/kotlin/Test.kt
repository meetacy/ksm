import ksm.annotation.LibraryConstructor
import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
import ksm.navigation.destination.name.name
import ksm.navigation.destination.navigate
import ksm.navigation.navigationStateController
import ksm.plugin.Plugin
import ksm.pluginStateController
import kotlin.test.Test

class KsmTest {

    @OptIn(LibraryConstructor::class)
    @Test
    fun ksmTest() {
        val controller = navigationStateController {
            install(PrintPlugin)
        }
        controller.navigate("Start")
    }
}

object PrintPlugin : Plugin.Singleton<PrintPlugin> {

    @MutateContext
    override fun install(context: StateContext): StateContext {
        context.addConfigurationInterceptor(Configuration)
        return context
    }

    private object Configuration : ConfigurationInterceptor {
        @MutateContext
        override fun onConfigure(context: StateContext): StateContext {
            context.addLifecycleInterceptor(Lifecycle)
            return context
        }
    }

    private object Lifecycle : LifecycleInterceptor {

        override fun onCreate(context: StateContext) {
            println("Hello, current state = ${context.name}")
        }
    }
}
