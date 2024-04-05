package dev.ceviz.exceptions

class HandlerNotFoundException(handlerName: String) : Exception("$handlerName handler not found")
