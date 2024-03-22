package ioc

interface DependencyFactory {
    fun <T> getSubTypesOf(clazz: Class<T>): Collection<Class<T>>
    fun <T> getSingletonInstanceOf(clazz: Class<T>): T
}
