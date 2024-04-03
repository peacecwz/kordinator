package com.peacecwz.registry

import com.peacecwz.behavior.Behavior
import com.peacecwz.command.Command
import com.peacecwz.command.CommandHandler
import com.peacecwz.exceptions.HandlerNotFoundException
import com.peacecwz.ioc.DependencyContainer
import com.peacecwz.ioc.DependencyFactory
import com.peacecwz.message.Message
import com.peacecwz.message.MessageHandler
import com.peacecwz.query.Query
import com.peacecwz.query.QueryHandler

class RegistryImpl(
    dependencyFactory: DependencyFactory
) : Registry {
    private val registry = DependencyContainer(dependencyFactory)
    override fun <TCommand : Command> getCommandHandler(classOfCommand: Class<TCommand>): CommandHandler<TCommand> {
        val handler = registry.commandMap[classOfCommand]?.get()
            ?: throw HandlerNotFoundException(classOfCommand.name)
        return handler as CommandHandler<TCommand>
    }

    override fun <TQuery : Query<TResult>, TResult> getQueryHandler(classOfQuery: Class<TQuery>): QueryHandler<TQuery, TResult> {
        val handler = registry.queryMap[classOfQuery]?.get()
            ?: throw HandlerNotFoundException(classOfQuery.name)
        return handler as QueryHandler<TQuery, TResult>
    }

    override fun <TMessage : Message> getMessageHandler(classOfMessage: Class<TMessage>): Collection<MessageHandler<TMessage>> {
        return registry.messageMap
            .filter { (k, _) ->
                k.isAssignableFrom(classOfMessage)
            }
            .flatMap { (_, v) ->
                v.map {
                    it.get() as MessageHandler<TMessage>
                }
            }
    }

    override fun getBehaviors(): Collection<Behavior> {
        return registry.behaviorSet.map { it.get() }
    }

}
