package dev.ceviz

import dev.ceviz.command.Command
import dev.ceviz.dispatchers.DispatchStrategy
import dev.ceviz.message.Message
import dev.ceviz.query.Query

interface Mediator {
    suspend fun <TQuery : Query<TResult>, TResult> send(query: TQuery): TResult
    suspend fun <TCommand : Command> send(command: TCommand)
    suspend fun <TMessage : Message> send(message: TMessage)
    suspend fun <TMessage : Message> send(message: TMessage, dispatchStrategy: DispatchStrategy)
}
