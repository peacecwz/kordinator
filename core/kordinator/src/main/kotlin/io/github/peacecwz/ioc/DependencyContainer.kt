package io.github.peacecwz.ioc

import io.github.peacecwz.behavior.Behavior
import io.github.peacecwz.behavior.BehaviorFactory
import io.github.peacecwz.command.Command
import io.github.peacecwz.command.CommandFactory
import io.github.peacecwz.command.CommandHandler
import io.github.peacecwz.message.Message
import io.github.peacecwz.message.MessageFactory
import io.github.peacecwz.message.MessageHandler
import io.github.peacecwz.query.Query
import io.github.peacecwz.query.QueryFactory
import io.github.peacecwz.query.QueryHandler
import io.github.peacecwz.registry.RegistryProvider

internal class DependencyContainer(dependencyFactory: DependencyFactory) : RegistryProvider() {
    val commandMap = HashMap<Class<*>, CommandFactory<CommandHandler<Command>>>()
    val queryMap = HashMap<Class<*>, QueryFactory<QueryHandler<*, *>>>()
    val messageMap = HashMap<Class<*>, MutableList<MessageFactory<MessageHandler<*>>>>()
    val behaviorSet = HashSet<BehaviorFactory<*>>()

    init {
        register<QueryHandler<Query<*>, *>, Query<*>>(dependencyFactory) { key, value ->
            queryMap[key] = QueryFactory(dependencyFactory, value as Class<QueryHandler<*, *>>)
        }

        register<CommandHandler<Command>, Command>(dependencyFactory) { key, value ->
            commandMap[key] = CommandFactory(dependencyFactory, value)
        }

        register<MessageHandler<Message>, Message>(dependencyFactory) { key, value ->
            messageMap.getOrPut(key) { mutableListOf() }
                .add(MessageFactory(dependencyFactory, value as Class<MessageHandler<*>>))
        }

        register<Behavior>(dependencyFactory) { handler ->
            behaviorSet.add(BehaviorFactory(dependencyFactory, handler))
        }
    }
}
