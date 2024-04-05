package dev.ceviz.ioc

import dev.ceviz.behavior.Behavior
import dev.ceviz.behavior.BehaviorFactory
import dev.ceviz.command.Command
import dev.ceviz.command.CommandFactory
import dev.ceviz.command.CommandHandler
import dev.ceviz.message.Message
import dev.ceviz.message.MessageFactory
import dev.ceviz.message.MessageHandler
import dev.ceviz.query.Query
import dev.ceviz.query.QueryFactory
import dev.ceviz.query.QueryHandler
import dev.ceviz.registry.RegistryProvider

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
