import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ParallelNoWaitDispatchStrategy : DispatchStrategy {
    override suspend fun <T : Message> publish(
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
