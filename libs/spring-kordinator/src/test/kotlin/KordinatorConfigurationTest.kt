import dev.ceviz.KordinatorConfiguration
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [KordinatorConfiguration::class])
class KordinatorConfigurationTest {

    @Autowired
    lateinit var mediator: dev.ceviz.Mediator

    @Test
    fun contextLoads() {
        assertNotNull(mediator)
    }
}
