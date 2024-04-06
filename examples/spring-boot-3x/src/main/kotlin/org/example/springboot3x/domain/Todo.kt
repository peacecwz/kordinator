package org.example.springboot3x.domain

data class Todo(
    var id: Int,
    var title: String,
    var description: String,
    var completed: Boolean
)
