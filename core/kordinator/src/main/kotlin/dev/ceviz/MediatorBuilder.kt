package dev.ceviz

import dev.ceviz.dispatchers.DispatchStrategy
import dev.ceviz.dispatchers.ThrowableDispatchStrategy
import dev.ceviz.ioc.DependencyFactory
import dev.ceviz.registry.Registry
import dev.ceviz.registry.RegistryImpl

class MediatorBuilder(
    private val dependencyFactory: DependencyFactory
) {
    private var defaultDispatchStrategy: DispatchStrategy = ThrowableDispatchStrategy()

    fun build(registry: Registry = RegistryImpl(dependencyFactory)): dev.ceviz.Mediator {
        return dev.ceviz.MediatorImpl(registry, defaultDispatchStrategy)
    }

    fun withDispatchStrategy(dispatchStrategy: DispatchStrategy): dev.ceviz.MediatorBuilder {
        this.defaultDispatchStrategy = dispatchStrategy
        return this
    }
}
