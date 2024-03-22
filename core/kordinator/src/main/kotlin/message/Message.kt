interface Message

interface MessageHandler<in T> where T : Message {
    suspend fun handle(message: T)
}
