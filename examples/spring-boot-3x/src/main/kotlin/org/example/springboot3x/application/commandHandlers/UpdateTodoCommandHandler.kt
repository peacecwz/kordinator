package org.example.springboot3x.application.commandHandlers

import dev.ceviz.command.CommandHandler
import org.example.springboot3x.application.commands.UpdateTodoCommand
import org.example.springboot3x.application.repositories.TodoRepository
import org.springframework.stereotype.Service

@Service
class UpdateTodoCommandHandler(
    private val repository: TodoRepository
) : CommandHandler<UpdateTodoCommand> {
    override suspend fun handle(command: UpdateTodoCommand) {
        val todo = repository.findById(command.id)
        todo?.let {
            it.completed = command.completed
            repository.update(it)
        }
    }
}
