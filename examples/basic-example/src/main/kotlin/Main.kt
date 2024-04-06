package org.example

import dev.ceviz.MediatorBuilder
import dev.ceviz.ioc.DependencyFactory
import org.example.commandHandlers.TestCommandHandler
import org.example.commands.TestCommand
import org.example.queries.TestQuery
import org.example.queryHandlers.TestQueryHandler

suspend fun main() {
    val testCommandHandler = TestCommandHandler()
    val testQueryHandler = TestQueryHandler()
    val dependencyFactory = TestDependencyFactory(
        hashMapOf(
            TestCommandHandler::class.java to testCommandHandler,
            TestQueryHandler::class.java to testQueryHandler
        )
    )

    val mediator = MediatorBuilder(dependencyFactory)
        .build()

    val message = mediator.send(TestQuery("info"))
    mediator.send(TestCommand(message))
}

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
