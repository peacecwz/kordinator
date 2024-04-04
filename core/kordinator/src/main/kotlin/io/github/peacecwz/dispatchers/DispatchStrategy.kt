package io.github.peacecwz.dispatchers

import io.github.peacecwz.message.Message
import io.github.peacecwz.message.MessageHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchStrategy {
    suspend fun <T : Message> dispatch(
        message: T,
        messageHandlers: Collection<MessageHandler<T>>,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    )
}
