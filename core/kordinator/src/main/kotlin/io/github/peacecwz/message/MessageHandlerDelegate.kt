package io.github.peacecwz.message

typealias MessageHandlerDelegate<TMessage, TResult> = suspend (TMessage) -> TResult
