import com.peacecwz.MediatorBuilder
import fixtures.*
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MediatorTests {
    @Test
    fun `should send and handle command`() {
        // given
        val mockHandler = mockk<TestCommandHandler>(relaxed = true)
        val dependencyFactory = TestDependencyFactory(
            hashMapOf(
                TestCommandHandler::class.java to mockHandler
            )
        )
        val mediator = MediatorBuilder(dependencyFactory).build()
        val command = TestCommand("hello")

        // when
        runBlocking { mediator.send(command) }

        // then
        coVerify(exactly = 1) {
            mockHandler.handle(command)
        }
    }

    @Test
    fun `should send and handle query`() {
        // given
        val testValue = "hello"
        val mockHandler = mockk<TestQueryHandler>(relaxed = true)
        val dependencyFactory = TestDependencyFactory(
            hashMapOf(
                TestQueryHandler::class.java to mockHandler
            )
        )
        val mediator = MediatorBuilder(dependencyFactory).build()
        val query = TestQuery(testValue)
        coEvery { mockHandler.handle(query) } returns "Handler: $testValue"

        // when
        val result = runBlocking { mediator.send(query) }

        // then
        coVerify(exactly = 1) {
            mockHandler.handle(query)
        }
        assert(result == "Handler: $testValue")
    }

    @Test
    fun `should send and handle query without mock`() {
        // given
        val testValue = "hello"
        val mockHandler = TestQueryHandler()
        val dependencyFactory = TestDependencyFactory(
            hashMapOf(
                TestQueryHandler::class.java to mockHandler
            )
        )
        val mediator = MediatorBuilder(dependencyFactory).build()
        val query = TestQuery(testValue)

        // when
        val result = runBlocking { mediator.send(query) }

        // then
        assert(result == "Handler: $testValue")
    }
}
