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
