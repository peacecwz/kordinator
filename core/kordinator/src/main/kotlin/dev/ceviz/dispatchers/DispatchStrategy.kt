package dev.ceviz.dispatchers

import dev.ceviz.message.Message
import dev.ceviz.message.MessageHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchStrategy {
    suspend fun <T : Message> dispatch(
        message: T,
        messageHandlers: Collection<MessageHandler<T>>,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    )
}
