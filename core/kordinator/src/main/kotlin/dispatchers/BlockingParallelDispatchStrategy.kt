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
