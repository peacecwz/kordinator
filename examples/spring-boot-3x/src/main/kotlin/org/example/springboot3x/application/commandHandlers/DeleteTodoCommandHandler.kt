package org.example.springboot3x.application.commandHandlers

import dev.ceviz.command.CommandHandler
import org.example.springboot3x.application.commands.DeleteTodoCommand
import org.example.springboot3x.application.repositories.TodoRepository
import org.springframework.stereotype.Service

@Service
class DeleteTodoCommandHandler(
    private val repository: TodoRepository
) : CommandHandler<DeleteTodoCommand> {
    override suspend fun handle(command: DeleteTodoCommand) {
        repository.deleteById(command.id)
    }
}
