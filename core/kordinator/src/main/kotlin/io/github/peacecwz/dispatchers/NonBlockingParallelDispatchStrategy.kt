package io.github.peacecwz.dispatchers

import io.github.peacecwz.message.Message
import io.github.peacecwz.message.MessageHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NonBlockingParallelDispatchStrategy : DispatchStrategy {
    override suspend fun <T : Message> dispatch(
        message: T,
        messageHandlers: Collection<MessageHandler<T>>,
        dispatcher: CoroutineDispatcher
    ) {
        coroutineScope {
            withContext(dispatcher) {
                messageHandlers.forEach {
                    launch {
                        it.handle(message)
                    }
                }
            }
        }
    }
}
