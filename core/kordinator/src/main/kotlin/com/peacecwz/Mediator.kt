package com.peacecwz

import com.peacecwz.command.Command
import com.peacecwz.dispatchers.DispatchStrategy
import com.peacecwz.message.Message
import com.peacecwz.query.Query

interface Mediator {
    suspend fun <TQuery : Query<TResult>, TResult> send(query: TQuery): TResult
    suspend fun <TCommand : Command> send(command: TCommand)
    suspend fun <TMessage : Message> send(message: TMessage)
    suspend fun <TMessage : Message> send(message: TMessage, dispatchStrategy: DispatchStrategy)
}
