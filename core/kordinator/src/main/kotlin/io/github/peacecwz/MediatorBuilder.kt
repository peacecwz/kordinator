package io.github.peacecwz

import io.github.peacecwz.dispatchers.DispatchStrategy
import io.github.peacecwz.dispatchers.ThrowableDispatchStrategy
import io.github.peacecwz.ioc.DependencyFactory
import io.github.peacecwz.registry.Registry
import io.github.peacecwz.registry.RegistryImpl

class MediatorBuilder(
    private val dependencyFactory: DependencyFactory
) {
    private var defaultDispatchStrategy: DispatchStrategy = ThrowableDispatchStrategy()

    fun build(registry: Registry = RegistryImpl(dependencyFactory)): io.github.peacecwz.Mediator {
        return io.github.peacecwz.MediatorImpl(registry, defaultDispatchStrategy)
    }

    fun withDispatchStrategy(dispatchStrategy: DispatchStrategy): io.github.peacecwz.MediatorBuilder {
        this.defaultDispatchStrategy = dispatchStrategy
        return this
    }
}
