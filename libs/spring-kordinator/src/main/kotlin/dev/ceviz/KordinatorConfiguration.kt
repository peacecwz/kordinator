package dev.ceviz

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean

@AutoConfiguration
open class KordinatorConfiguration {
    @Bean
    open fun kordinatorBeanFactory(
        applicationContext: ApplicationContext
    ): SpringBeanFactory {
        return SpringBeanFactory(applicationContext)
    }

    @Bean
    open fun mediator(kordinatorBeanFactory: SpringBeanFactory): Mediator {
        return MediatorBuilder(kordinatorBeanFactory).build()
    }
}
