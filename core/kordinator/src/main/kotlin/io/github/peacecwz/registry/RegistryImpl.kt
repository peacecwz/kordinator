package io.github.peacecwz.registry

import io.github.peacecwz.behavior.Behavior
import io.github.peacecwz.command.Command
import io.github.peacecwz.command.CommandHandler
import io.github.peacecwz.exceptions.HandlerNotFoundException
import io.github.peacecwz.ioc.DependencyContainer
import io.github.peacecwz.ioc.DependencyFactory
import io.github.peacecwz.message.Message
import io.github.peacecwz.message.MessageHandler
import io.github.peacecwz.query.Query
import io.github.peacecwz.query.QueryHandler

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
