package com.peacecwz.command

interface Command

interface CommandHandler<TCommand : Command> {
    suspend fun handle(command: TCommand)
}
