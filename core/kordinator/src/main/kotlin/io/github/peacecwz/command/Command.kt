package io.github.peacecwz.command

interface Command

interface CommandHandler<TCommand : Command> {
    suspend fun handle(command: TCommand)
}
