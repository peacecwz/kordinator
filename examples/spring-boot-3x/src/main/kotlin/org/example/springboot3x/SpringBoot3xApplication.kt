package org.example.springboot3x

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBoot3xApplication

fun main(args: Array<String>) {
    runApplication<SpringBoot3xApplication>(*args)
}
