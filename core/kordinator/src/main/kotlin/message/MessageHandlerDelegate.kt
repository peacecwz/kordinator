typealias MessageHandlerDelegate<TMessage, TResult> = suspend (TMessage) -> TResult
