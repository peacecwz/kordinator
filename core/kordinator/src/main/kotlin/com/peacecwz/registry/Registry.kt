package com.peacecwz.registry

import com.peacecwz.behavior.Behavior
import com.peacecwz.command.Command
import com.peacecwz.command.CommandHandler
import com.peacecwz.message.Message
import com.peacecwz.message.MessageHandler
import com.peacecwz.query.Query
import com.peacecwz.query.QueryHandler

interface Registry {
    fun <TCommand : Command> getCommandHandler(classOfCommand: Class<TCommand>): CommandHandler<TCommand>

    fun <TQuery : Query<TResult>, TResult> getQueryHandler(classOfQuery: Class<TQuery>): QueryHandler<TQuery, TResult>

    fun <TMessage : Message> getMessageHandler(classOfMessage: Class<TMessage>): Collection<MessageHandler<TMessage>>

    fun getBehaviors(): Collection<Behavior>
}
