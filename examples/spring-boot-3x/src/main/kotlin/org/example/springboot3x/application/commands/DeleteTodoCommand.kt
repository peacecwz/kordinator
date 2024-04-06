package org.example.springboot3x.application.commands

import dev.ceviz.command.Command
import org.springframework.web.bind.annotation.PathVariable

data class DeleteTodoCommand(
    @PathVariable("id") val id: Int,
) : Command
