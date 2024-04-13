package ksm.navigation.destination.data

import ksm.StateController

public inline fun <reified T> StateController.receive(): T {
    return context.receive()
}
