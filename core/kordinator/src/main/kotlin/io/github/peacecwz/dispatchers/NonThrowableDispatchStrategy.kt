package io.github.peacecwz.dispatchers

import io.github.peacecwz.exceptions.MessageExceptions
import io.github.peacecwz.message.Message
import io.github.peacecwz.message.MessageHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class NonThrowableDispatchStrategy : DispatchStrategy {
    override suspend fun <T : Message> dispatch(
        message: T,
        messageHandlers: Collection<MessageHandler<T>>,
        dispatcher: CoroutineDispatcher
    ) {
        coroutineScope {
            withContext(dispatcher) {
                val exceptions = mutableListOf<Throwable>()
                messageHandlers.forEach {
                    try {
                        it.handle(message)
                    } catch (e: Exception) {
                        exceptions.add(e)
                    }
                }

                if (exceptions.isNotEmpty()) {
                    throw MessageExceptions(exceptions.toTypedArray())
                }
            }
        }
    }
}
