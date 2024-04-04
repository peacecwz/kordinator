package io.github.peacecwz.dispatchers

import io.github.peacecwz.message.Message
import io.github.peacecwz.message.MessageHandler
import kotlinx.coroutines.*

class BlockingParallelDispatchStrategy : DispatchStrategy {
    override suspend fun <T : Message> dispatch(
        message: T,
        messageHandlers: Collection<MessageHandler<T>>,
        dispatcher: CoroutineDispatcher
    ) {
        coroutineScope {
            withContext(dispatcher) {
                val deferredResults = messageHandlers.map {
                    async {
                        it.handle(message)
                    }
                }
                deferredResults.awaitAll()
            }
        }
    }
}
