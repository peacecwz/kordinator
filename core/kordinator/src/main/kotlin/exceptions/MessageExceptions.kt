package com.peacecwz.exceptions

class MessageExceptions(val exceptions: Collection<Throwable>) : RuntimeException() {
    constructor(exceptions: Array<Throwable>) : this(exceptions.toList())
}


