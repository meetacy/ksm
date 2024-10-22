import ksm.annotation.LibraryApi
import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.configuration.interceptor.ConfigurationInterceptor
import ksm.configuration.interceptor.addConfigurationInterceptor
import ksm.lifecycle.interceptor.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
import ksm.navigation.state.name.name
import ksm.navigation.state.navigate
import ksm.navigation.navigationRuntime
import ksm.plugin.Plugin
import kotlin.test.Test

class KsmTest {

    @OptIn(LibraryApi::class)
    @Test
    fun ksmTest() {
        val controller = navigationRuntime {
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
