package com.peacecwz.ioc

interface DependencyFactory {
    fun <T> getSubTypesOf(clazz: Class<T>): Collection<Class<T>>
    fun <T> getInstanceOf(clazz: Class<T>): T
}
