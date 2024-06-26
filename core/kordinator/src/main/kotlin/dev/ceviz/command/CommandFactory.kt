package dev.ceviz.command

import dev.ceviz.ioc.DependencyFactory

internal class CommandFactory<H : CommandHandler<*>>(
    private val dependencyFactory: DependencyFactory,
    private val type: Class<H>
) {
    fun get(): H {
        return dependencyFactory.getInstanceOf(type)
    }
}
