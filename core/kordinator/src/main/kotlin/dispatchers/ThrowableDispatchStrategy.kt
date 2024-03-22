import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class StopOnExceptionDispatchStrategy : DispatchStrategy {
    override suspend fun <T : Message> publish(
        message: T,
        messageHandlers: Collection<MessageHandler<T>>,
        dispatcher: CoroutineDispatcher
    ) {
        coroutineScope {
            withContext(dispatcher) {
                messageHandlers.forEach { it.handle(message) }
            }
        }
    }
}
