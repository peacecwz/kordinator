package dev.ceviz.command

interface Command

interface CommandHandler<TCommand : Command> {
    suspend fun handle(command: TCommand)
}
