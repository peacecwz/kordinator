package dev.ceviz.dispatchers

import dev.ceviz.message.Message
import dev.ceviz.message.MessageHandler
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
