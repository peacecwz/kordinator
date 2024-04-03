package com.peacecwz.dispatchers

import com.peacecwz.message.Message
import com.peacecwz.message.MessageHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchStrategy {
    suspend fun <T : Message> dispatch(
        message: T,
        messageHandlers: Collection<MessageHandler<T>>,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    )
}
