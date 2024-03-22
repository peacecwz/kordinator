interface Mediator {
    suspend fun <TQuery : Query<TResult>, TResult> send(query: TQuery): TResult
    suspend fun <TCommand : Command> send(command: TCommand)
    suspend fun <TMessage : Message> send(message: TMessage)
    suspend fun <TMessage : Message> send(message: TMessage, dispatchStrategy: DispatchStrategy)
}
