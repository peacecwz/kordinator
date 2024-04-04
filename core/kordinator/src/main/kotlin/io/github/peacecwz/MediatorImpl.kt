package io.github.peacecwz

import io.github.peacecwz.behavior.Behavior
import io.github.peacecwz.command.Command
import io.github.peacecwz.dispatchers.DispatchStrategy
import io.github.peacecwz.dispatchers.ThrowableDispatchStrategy
import io.github.peacecwz.message.Message
import io.github.peacecwz.message.MessageHandlerDelegate
import io.github.peacecwz.query.Query
import io.github.peacecwz.registry.Registry

internal class MediatorImpl(
    private val registry: Registry,
    private val defaultDispatchStrategy: DispatchStrategy = ThrowableDispatchStrategy()
) : io.github.peacecwz.Mediator {
    override suspend fun <TQuery : Query<TResult>, TResult> send(query: TQuery): TResult =
        processBehaviors(
            registry.getBehaviors(),
            query
        ) {
            registry
                .getQueryHandler(query.javaClass)
                .handle(query)
        }

    override suspend fun <TCommand : Command> send(command: TCommand) =
        processBehaviors(
            registry.getBehaviors(),
            command
        ) {
            registry
                .getCommandHandler(command.javaClass)
                .handle(command)
        }

    override suspend fun <TMessage : Message> send(message: TMessage) = send(message, defaultDispatchStrategy)

    override suspend fun <TMessage : Message> send(
        message: TMessage,
        dispatchStrategy: DispatchStrategy
    ) = processBehaviors(
        registry.getBehaviors(),
        message
    ) {
        dispatchStrategy.dispatch(message, registry.getMessageHandler(message.javaClass))
    }

    private suspend fun <TMessage, TResult> processBehaviors(
        behaviors: Collection<Behavior>,
        message: TMessage,
        handler: MessageHandlerDelegate<TMessage, TResult>
    ): TResult =
        behaviors
            .reversed()
            .fold(handler)
            { next, pipeline ->
                {
                    pipeline.handle(message) {
                        next(it)
                    }
                }
            }(message)
}
