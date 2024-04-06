package org.example.springboot3x.infrastructure.persistance

import org.example.springboot3x.application.repositories.TodoRepository
import org.example.springboot3x.domain.Todo
import org.springframework.stereotype.Service

@Service
class TodoRepositoryImpl : TodoRepository {
    private val todos = mutableListOf<Todo>()

    override fun findAll(): List<Todo> {
        return todos
    }

    override fun findById(id: Int): Todo? {
        return todos.find { it.id == id }
    }

    override fun save(todo: Todo): Todo {
        val id = if (todos.isEmpty()) 1 else todos.last().id + 1
        val newTodo = todo.copy(id = id)
        todos.add(newTodo)
        return newTodo
    }

    override fun update(todo: Todo): Todo {
        val index = todos.indexOfFirst { it.id == todo.id }
        todos[index] = todo
        return todo
    }

    override fun deleteById(id: Int) {
        todos.removeIf { it.id == id }
    }
}

