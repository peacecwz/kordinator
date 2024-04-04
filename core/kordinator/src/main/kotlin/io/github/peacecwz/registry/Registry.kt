package io.github.peacecwz.registry

import io.github.peacecwz.behavior.Behavior
import io.github.peacecwz.command.Command
import io.github.peacecwz.command.CommandHandler
import io.github.peacecwz.message.Message
import io.github.peacecwz.message.MessageHandler
import io.github.peacecwz.query.Query
import io.github.peacecwz.query.QueryHandler

interface Registry {
    fun <TCommand : Command> getCommandHandler(classOfCommand: Class<TCommand>): CommandHandler<TCommand>

    fun <TQuery : Query<TResult>, TResult> getQueryHandler(classOfQuery: Class<TQuery>): QueryHandler<TQuery, TResult>

    fun <TMessage : Message> getMessageHandler(classOfMessage: Class<TMessage>): Collection<MessageHandler<TMessage>>

    fun getBehaviors(): Collection<Behavior>
}
