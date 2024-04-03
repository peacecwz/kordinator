package com.peacecwz.exceptions

class HandlerNotFoundException(handlerName: String) : Exception("$handlerName handler not found")
