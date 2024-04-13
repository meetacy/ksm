import ksm.annotation.MutateContext
import ksm.context.StateContext
import ksm.context.configuration.interceptor.ConfigurationInterceptor
import ksm.context.configuration.interceptor.addConfigurationInterceptor
import ksm.createRawStateController
import ksm.lifecycle.LifecycleInterceptor
import ksm.lifecycle.addLifecycleInterceptor
import ksm.plugin.Plugin
import ksm.state.launch
import ksm.state.name.stateName
import kotlin.test.Test

class KsmTest {

    @Test
    fun ksmTest() {
        val controller = createRawStateController {
            install(PrintPlugin)
        }
        controller.launch("Start")
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
            println("Hello, current state = ${context.stateName}")
        }
    }
}
