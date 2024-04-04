package io.github.peacecwz.exceptions

class HandlerNotFoundException(handlerName: String) : Exception("$handlerName handler not found")
