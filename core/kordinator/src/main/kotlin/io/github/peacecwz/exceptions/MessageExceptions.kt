package io.github.peacecwz.exceptions

class MessageExceptions(private val exceptions: Array<Throwable>) : RuntimeException() {
    override val message: String
        get() = exceptions.joinToString { it.message ?: "" }
}


