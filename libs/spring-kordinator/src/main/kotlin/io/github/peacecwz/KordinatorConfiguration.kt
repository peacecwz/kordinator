package io.github.peacecwz

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean

@AutoConfiguration
open class KordinatorConfiguration {
    @Bean
    open fun kordinatorSpringBeanFactory(
        applicationContext: ApplicationContext
    ): SpringBeanFactory {
        return SpringBeanFactory(applicationContext)
    }

    @Bean
    open fun kordinator(factory: SpringBeanFactory): Mediator {
        return MediatorBuilder(factory).build()
    }
}
