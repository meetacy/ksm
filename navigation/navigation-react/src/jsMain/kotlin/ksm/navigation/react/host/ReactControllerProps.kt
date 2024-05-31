package ksm.navigation.react.host

import ksm.navigation.react.ReactController
import org.w3c.dom.HTMLStyleElement
import react.Props

public external interface ReactControllerProps : Props {
    public var controller: ReactController
    public var startStateName: String
    public var style: (HTMLStyleElement) -> Unit
}
