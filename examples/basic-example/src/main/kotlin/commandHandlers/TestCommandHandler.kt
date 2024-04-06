package org.example.commandHandlers

import dev.ceviz.command.CommandHandler
import org.example.commands.TestCommand

class TestCommandHandler : CommandHandler<TestCommand> {
    override suspend fun handle(command: TestCommand) {
        println("Test command message: ${command.message}")
    }
}
