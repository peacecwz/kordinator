package com.peacecwz.dispatchers

import com.peacecwz.message.Message
import com.peacecwz.message.MessageHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class ThrowableDispatchStrategy : DispatchStrategy {
    override suspend fun <T : Message> dispatch(
        message: T,
        messageHandlers: Collection<MessageHandler<T>>,
        dispatcher: CoroutineDispatcher
    ) {
        coroutineScope {
            withContext(dispatcher) {
                messageHandlers.forEach {
                    it.handle(message)
                }
            }
        }
    }
}
