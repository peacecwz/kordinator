plugins {
    kotlin("jvm")
}

object Versions {
    const val COROUTINE_VERSION = "1.8.0"
    const val SPRING_BOOT = "3.2.4"
    const val MOCKK_VERSION = "1.12.0"
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    implementation(project(":core:kordinator"))
    implementation("org.springframework.boot:spring-boot-autoconfigure:${Versions.SPRING_BOOT}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE_VERSION}")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test:${Versions.SPRING_BOOT}")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.mockk:mockk:${Versions.MOCKK_VERSION}")
}
