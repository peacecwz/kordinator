import java.lang.reflect.ParameterizedType
import java.lang.reflect.TypeVariable

abstract class RegistryProvider {
    protected inline fun <reified THandler : Any, TParameter> register(
        dependencyFactory: DependencyFactory,
        registryProvider: (key: Class<TParameter>, value: Class<THandler>) -> Unit
    ) = dependencyFactory.getSubTypesOf(THandler::class.java).forEach {
        register<THandler, TParameter>(it) { key, value ->
            registryProvider(key as Class<TParameter>, value as Class<THandler>)
        }
    }

    protected inline fun <reified THandler : Any, TParameter> register(
        handler: Class<*>,
        registryProvider: (key: Class<*>, value: Class<*>) -> Unit
    ) {
        val clazz = THandler::class.java

        if (!clazz.isAssignableFrom(handler)) {
            return
        }

        handler.genericInterfaces
            .filterIsInstance<ParameterizedType>()
            .map { getParametersFromClass(it) }
            .forEach { registryProvider(it, handler) }

        when (handler.genericSuperclass) {
            is ParameterizedType -> {
                val inheritedHandler = (handler.genericSuperclass as ParameterizedType).rawType as Class<*>

                inheritedHandler.genericInterfaces
                    .filterIsInstance<ParameterizedType>()
                    .map { getParametersFromClass(handler.genericSuperclass as ParameterizedType) }
                    .forEach { registryProvider(it, handler) }
            }

            is Class<*> -> {
                val inheritedHandler = handler.genericSuperclass as Class<*>
                if (!clazz.isAssignableFrom(inheritedHandler)) {
                    return
                }

                inheritedHandler.genericInterfaces
                    .filterIsInstance<ParameterizedType>()
                    .map { getParametersFromClass(it) }
                    .forEach { registryProvider(it, handler) }
            }
        }
    }

    protected inline fun <reified T> register(
        dependencyFactory: DependencyFactory,
        registryProvider: (value: Class<T>) -> Unit
    ) = dependencyFactory.getSubTypesOf(T::class.java).forEach { handler ->
        register<T>(handler) { value -> registryProvider(value as Class<T>) }
    }

    protected inline fun <reified T> register(
        handler: Class<*>,
        registryProvider: (value: Class<*>) -> Unit
    ) {
        val clazz = T::class.java
        if (!clazz.isAssignableFrom(handler)) {
            return
        }

        registryProvider(handler)
    }

    protected fun getParametersFromClass(genericInterface: ParameterizedType): Class<*> =
        when (val typeArgument = genericInterface.actualTypeArguments[0]) {
            is ParameterizedType -> typeArgument.rawType as Class<*>
            is TypeVariable<*> -> getParametersFromClass((genericInterface.rawType as Class<*>).genericInterfaces[0] as ParameterizedType)
            else -> typeArgument as Class<*>
        }
}
