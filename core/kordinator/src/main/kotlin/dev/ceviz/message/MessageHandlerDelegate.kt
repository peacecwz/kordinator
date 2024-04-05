package dev.ceviz.message

typealias MessageHandlerDelegate<TMessage, TResult> = suspend (TMessage) -> TResult
