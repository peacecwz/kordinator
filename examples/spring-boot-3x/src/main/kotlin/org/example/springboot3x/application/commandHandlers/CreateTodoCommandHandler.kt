package org.example.springboot3x.application.commandHandlers

import dev.ceviz.command.CommandHandler
import org.example.springboot3x.application.commands.CreateTodoCommand
import org.springframework.stereotype.Service
import org.example.springboot3x.application.repositories.TodoRepository
import org.example.springboot3x.domain.Todo

@Service
class CreateTodoCommandHandler(
    private val repository: TodoRepository
) : CommandHandler<CreateTodoCommand> {
    override suspend fun handle(command: CreateTodoCommand) {
        repository.save(
            Todo(
                id = 0,
                title = command.title,
                description = command.description,
                completed = false
            )
        )
    }

}
