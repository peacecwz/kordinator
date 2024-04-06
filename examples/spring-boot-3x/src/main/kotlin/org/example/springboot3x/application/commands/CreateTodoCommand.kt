package org.example.springboot3x.application.commands

import dev.ceviz.command.Command

data class CreateTodoCommand(
    val title: String,
    val description: String
): Command
