plugins {
    kotlin("jvm")
    id("maven-publish")
    id("signing")
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


publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "com.peacecwz"
            artifactId = "spring-3x-kordinator"
            version = (System.getenv("LIBRARY_VERSION") ?: "0.0.1").replace("/", "")

            from(components["java"])

            pom {
                name.set("Kordinator")
                description.set("A simple coroutine-based coordinator library")
                url.set("https://maven.pkg.github.com/peacecwz/kordinator")
                packaging = "jar"
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://github.com/peacecwz/kordinator/blob/main/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("peacecwz")
                        name.set("Baris Ceviz")
                        email.set("baris@ceviz.dev")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/peacecwz/kordinator.git")
                }
            }
        }
    }
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/peacecwz/kordinator")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(System.getenv("SIGNING_KEY"), System.getenv("SIGNING_PASSWORD"))
    sign(publishing.publications)
}
