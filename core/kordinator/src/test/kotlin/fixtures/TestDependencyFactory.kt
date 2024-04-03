package fixtures

import com.peacecwz.ioc.DependencyFactory

class TestDependencyFactory(
    private val handlerMap: HashMap<Class<*>, Any>
) : DependencyFactory {
    override fun <T> getSubTypesOf(clazz: Class<T>): Collection<Class<T>> {
        return handlerMap
            .filter {
                it.key.interfaces.contains(clazz)
            }
            .map {
                it.key as Class<T>
            }
    }

    override fun <T> getInstanceOf(clazz: Class<T>): T {
        return handlerMap[clazz] as T
    }
}
