package org.example.springboot3x.infrastructure.web

import dev.ceviz.Mediator
import org.example.springboot3x.application.commands.CreateTodoCommand
import org.example.springboot3x.application.commands.DeleteTodoCommand
import org.example.springboot3x.application.commands.UpdateTodoCommand
import org.example.springboot3x.application.queries.GetTodosQuery
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos")
@RestController
class TodosController(private val mediator: Mediator) {
    @GetMapping
    suspend fun getTodos(query: GetTodosQuery) = mediator.send(query)

    @PostMapping
    suspend fun createTodo(@RequestBody command: CreateTodoCommand) = mediator.send(command)

    @PatchMapping("/{id}")
    suspend fun markAsComplete(@PathVariable("id") id: Int, @RequestBody command: UpdateTodoCommand) {
        command.id = id
        mediator.send(command)
    }

    @DeleteMapping("/{id}")
    suspend fun delete(command: DeleteTodoCommand) = mediator.send(command)
}
