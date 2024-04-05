package dev.ceviz.behavior

import dev.ceviz.message.MessageHandlerDelegate

interface Behavior {
    suspend fun <TMessage, TResult> handle(
        message: TMessage,
        next: MessageHandlerDelegate<TMessage, TResult>
    ): TResult
}
