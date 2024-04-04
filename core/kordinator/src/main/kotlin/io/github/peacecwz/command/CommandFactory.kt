package io.github.peacecwz.command

import io.github.peacecwz.ioc.DependencyFactory

internal class CommandFactory<H : CommandHandler<*>>(
    private val dependencyFactory: DependencyFactory,
    private val type: Class<H>
) {
    fun get(): H {
        return dependencyFactory.getInstanceOf(type)
    }
}
