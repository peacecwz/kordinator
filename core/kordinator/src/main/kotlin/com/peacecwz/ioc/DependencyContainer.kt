package com.peacecwz.ioc

import com.peacecwz.behavior.Behavior
import com.peacecwz.behavior.BehaviorFactory
import com.peacecwz.command.Command
import com.peacecwz.command.CommandFactory
import com.peacecwz.command.CommandHandler
import com.peacecwz.message.Message
import com.peacecwz.message.MessageFactory
import com.peacecwz.message.MessageHandler
import com.peacecwz.query.Query
import com.peacecwz.query.QueryFactory
import com.peacecwz.query.QueryHandler
import com.peacecwz.registry.RegistryProvider

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
