package io.github.peacecwz

import io.github.peacecwz.ioc.DependencyFactory
import org.springframework.context.ApplicationContext

class SpringBeanFactory(
    private val context: ApplicationContext
) : DependencyFactory {
    override fun <T> getSubTypesOf(clazz: Class<T>): Collection<Class<T>> {
        return context.getBeanNamesForType(clazz)
            .map {
                context.getType(it) as Class<T>
            }
    }

    override fun <T> getInstanceOf(clazz: Class<T>): T {
        return context.getBean(clazz)
    }
}
