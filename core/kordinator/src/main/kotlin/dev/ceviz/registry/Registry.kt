package dev.ceviz.registry

import dev.ceviz.behavior.Behavior
import dev.ceviz.command.Command
import dev.ceviz.command.CommandHandler
import dev.ceviz.message.Message
import dev.ceviz.message.MessageHandler
import dev.ceviz.query.Query
import dev.ceviz.query.QueryHandler

interface Registry {
    fun <TCommand : Command> getCommandHandler(classOfCommand: Class<TCommand>): CommandHandler<TCommand>

    fun <TQuery : Query<TResult>, TResult> getQueryHandler(classOfQuery: Class<TQuery>): QueryHandler<TQuery, TResult>

    fun <TMessage : Message> getMessageHandler(classOfMessage: Class<TMessage>): Collection<MessageHandler<TMessage>>

    fun getBehaviors(): Collection<Behavior>
}
