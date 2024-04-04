package io.github.peacecwz.behavior

import io.github.peacecwz.message.MessageHandlerDelegate

interface Behavior {
    suspend fun <TMessage, TResult> handle(
        message: TMessage,
        next: MessageHandlerDelegate<TMessage, TResult>
    ): TResult
}
