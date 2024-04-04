package io.github.peacecwz.behavior

import io.github.peacecwz.ioc.DependencyFactory

internal class BehaviorFactory<H : Behavior>(
    private val dependencyFactory: DependencyFactory,
    private val type: Class<H>
) {
    fun get(): H {
        return dependencyFactory.getInstanceOf(type)
    }
}
