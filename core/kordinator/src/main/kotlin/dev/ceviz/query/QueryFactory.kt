package dev.ceviz.query

import dev.ceviz.ioc.DependencyFactory

internal class QueryFactory<H : QueryHandler<*, *>>(
    private val dependencyFactory: DependencyFactory,
    private val type: Class<H>
) {
    fun get(): H {
        return dependencyFactory.getInstanceOf(type)
    }
}
