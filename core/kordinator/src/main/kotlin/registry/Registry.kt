
interface Registry {
    fun <TCommand : Command> getCommandHandler(classOfCommand: Class<TCommand>): CommandHandler<TCommand>

    fun <TQuery : Query<TResult>, TResult> getQueryHandler(classOfQuery: Class<TQuery>): QueryHandler<TQuery, TResult>

    fun <TMessage : Message> getMessageHandler(
        classOfMessage: Class<TMessage>
    ): Collection<MessageHandler<TMessage>>

    fun getPipelineBehaviors(): Collection<Behavior>
}
