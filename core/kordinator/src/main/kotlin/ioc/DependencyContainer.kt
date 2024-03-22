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
