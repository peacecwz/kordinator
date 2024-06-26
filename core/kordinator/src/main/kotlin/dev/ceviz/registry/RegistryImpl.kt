package dev.ceviz.registry

import dev.ceviz.behavior.Behavior
import dev.ceviz.command.Command
import dev.ceviz.command.CommandHandler
import dev.ceviz.exceptions.HandlerNotFoundException
import dev.ceviz.ioc.DependencyContainer
import dev.ceviz.ioc.DependencyFactory
import dev.ceviz.message.Message
import dev.ceviz.message.MessageHandler
import dev.ceviz.query.Query
import dev.ceviz.query.QueryHandler

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
