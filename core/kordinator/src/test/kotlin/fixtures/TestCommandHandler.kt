package fixtures

import Command
import CommandHandler

class TestCommandHandler : CommandHandler<TestCommand> {
    override suspend fun handle(command: TestCommand) {
        println("Handling command: ${command.value}")
    }
}
