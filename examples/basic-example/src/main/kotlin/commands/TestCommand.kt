package org.example.commands

import dev.ceviz.command.Command

data class TestCommand(
    val message: String
) : Command
