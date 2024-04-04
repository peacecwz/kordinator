package fixtures

import io.github.peacecwz.command.CommandHandler

class TestCommandHandler : CommandHandler<TestCommand> {
    override suspend fun handle(command: TestCommand) {
        println("Handling command: ${command.value}")
    }
}
