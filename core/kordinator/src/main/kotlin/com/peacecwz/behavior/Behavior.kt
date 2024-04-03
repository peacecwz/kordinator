package com.peacecwz.behavior

import com.peacecwz.message.MessageHandlerDelegate

interface Behavior {
    suspend fun <TMessage, TResult> handle(
        message: TMessage,
        next: MessageHandlerDelegate<TMessage, TResult>
    ): TResult
}
