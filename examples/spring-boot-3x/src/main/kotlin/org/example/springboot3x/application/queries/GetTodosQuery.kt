package org.example.springboot3x.application.queries

import dev.ceviz.query.Query
import org.example.springboot3x.domain.Todo

data class GetTodosQuery : Query<List<Todo>>
