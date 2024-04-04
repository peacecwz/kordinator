package io.github.peacecwz

import io.github.peacecwz.command.Command
import io.github.peacecwz.dispatchers.DispatchStrategy
import io.github.peacecwz.message.Message
import io.github.peacecwz.query.Query

interface Mediator {
    suspend fun <TQuery : Query<TResult>, TResult> send(query: TQuery): TResult
    suspend fun <TCommand : Command> send(command: TCommand)
    suspend fun <TMessage : Message> send(message: TMessage)
    suspend fun <TMessage : Message> send(message: TMessage, dispatchStrategy: DispatchStrategy)
}
