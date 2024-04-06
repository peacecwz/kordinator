package org.example.springboot3x.application.commands

import dev.ceviz.command.Command

data class UpdateTodoCommand(
    var id: Int,
    val completed: Boolean
) : Command
