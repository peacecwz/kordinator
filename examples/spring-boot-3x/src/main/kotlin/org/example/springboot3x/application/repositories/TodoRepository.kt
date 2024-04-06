package org.example.springboot3x.application.repositories

import org.example.springboot3x.domain.Todo

interface TodoRepository {
    fun findAll(): List<Todo>
    fun findById(id: Int): Todo?
    fun save(todo: Todo): Todo
    fun update(todo: Todo): Todo
    fun deleteById(id: Int)
}
