package dev.ceviz.message

import dev.ceviz.ioc.DependencyFactory

internal class MessageFactory<H : MessageHandler<*>>(
    private val dependencyFactory: DependencyFactory,
    private val type: Class<H>
) {
    fun get(): H {
        return dependencyFactory.getInstanceOf(type)
    }
}
