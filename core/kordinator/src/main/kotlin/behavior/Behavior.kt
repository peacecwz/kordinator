interface Behavior {
    suspend fun <TMessage, TResult> handle(
        message: TMessage,
        next: MessageHandlerDelegate<TMessage, TResult>
    ): TResult
}
