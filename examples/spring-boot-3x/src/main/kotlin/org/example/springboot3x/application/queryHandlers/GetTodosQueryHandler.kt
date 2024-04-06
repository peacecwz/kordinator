package org.example.springboot3x.application.queryHandlers

import dev.ceviz.query.QueryHandler
import org.example.springboot3x.application.queries.GetTodosQuery
import org.example.springboot3x.domain.Todo
import org.springframework.stereotype.Service
import org.example.springboot3x.application.repositories.TodoRepository

@Service
class GetTodosQueryHandler(
    private val repository: TodoRepository
) : QueryHandler<GetTodosQuery, List<Todo>> {
    override suspend fun handle(query: GetTodosQuery): List<Todo> {
        return repository.findAll()
    }
}
