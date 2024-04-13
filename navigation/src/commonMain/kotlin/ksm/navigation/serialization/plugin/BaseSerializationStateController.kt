package ksm.navigation.serialization.plugin

import ksm.context.StateContext
import ksm.context.createChildContext
import ksm.navigation.destination.name.name
import ksm.navigation.destination.name.setName
import ksm.navigation.destination.parameters.setParameter
import ksm.navigation.serialization.BaseSerializationFormat
import ksm.navigation.stack.previousContextOrNull
import ksm.typed.TypedValue
import kotlin.reflect.typeOf

private typealias SerializedStackType = List<TypedValue<Map<String, TypedValue<*>>>>

internal class BaseSerializationStateController(
    private val format: BaseSerializationFormat
) : StateContext.Element {
    override val key = BaseSerializationStateController

    private val serializedStackType = typeOf<SerializedStackType>()

    @Suppress("UNCHECKED_CAST")
    fun restore(root: StateContext) {
        val serializedStack = format.decode(serializedStackType) as SerializedStackType
        var current = root
        for (serializedEntry in serializedStack) {
            current = decodeEntry(current, serializedEntry.data)
        }
    }

    private fun decodeEntry(
        context: StateContext,
        parameters: Map<String, TypedValue<*>>
    ): StateContext = context.createChildContext { child ->
        val name = parameters[STATE_NAME_KEY]?.data as String?
        if (name != null) {
            child.setName(name)
        }
        for ((key, value) in parameters) {
            if (key == STATE_NAME_KEY) continue
            child.setParameter(key, value)
        }
    }

    fun commit(context: StateContext, entry: BaseSerializationParametersInterceptor) {
        var current: StateContext? = context
        val serializedStack = mutableListOf<TypedValue<*>>()

        while (current != null) {
            val encodedEntry = encodeEntry(current, entry)
            serializedStack.add(
                // Make sure root context is always on top
                index = 0,
                element = encodedEntry
            )
            current = context.previousContextOrNull
        }

        val value = TypedValue.of(serializedStack.toList())
        format.encode(value)
    }

    private fun encodeEntry(
        context: StateContext,
        entry: BaseSerializationParametersInterceptor
    ): TypedValue<*> {
        val name = STATE_NAME_KEY to TypedValue.Generic.of(context.name)
        val map = entry.toMap() + name
        return TypedValue.of(map)
    }

    companion object : StateContext.Key<BaseSerializationStateController> {
        const val STATE_NAME_KEY = "ksm.serialization.plugin.STATE_NAME"
    }
}
