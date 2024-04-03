package com.peacecwz.message

typealias MessageHandlerDelegate<TMessage, TResult> = suspend (TMessage) -> TResult
