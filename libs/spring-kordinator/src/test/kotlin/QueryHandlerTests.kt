import fixtures.TestQuery
import fixtures.TestQueryHandler
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest(classes = [KordinatorConfiguration::class, TestQueryHandler::class])
class QueryHandlerTests {
    @Autowired
    lateinit var mediator: Mediator

    @Test
    fun `should handle query`() {
        // given
        val testValue = "test"
        val query = TestQuery(testValue)

        // when
        val result = runBlocking {
            mediator.send(query)
        }

        // then
        assert(result == "Handler: $testValue")
    }
}
