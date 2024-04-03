package com.peacecwz

import com.peacecwz.dispatchers.DispatchStrategy
import com.peacecwz.dispatchers.ThrowableDispatchStrategy
import com.peacecwz.ioc.DependencyFactory
import com.peacecwz.registry.Registry
import com.peacecwz.registry.RegistryImpl

class MediatorBuilder(
    private val dependencyFactory: DependencyFactory
) {
    private var defaultDispatchStrategy: DispatchStrategy = ThrowableDispatchStrategy()

    fun build(registry: Registry = RegistryImpl(dependencyFactory)): Mediator {
        return MediatorImpl(registry, defaultDispatchStrategy)
    }

    fun withDispatchStrategy(dispatchStrategy: DispatchStrategy): MediatorBuilder {
        this.defaultDispatchStrategy = dispatchStrategy
        return this
    }
}
