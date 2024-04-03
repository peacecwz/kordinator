plugins {
    kotlin("jvm")
    id("maven-publish")
    id("signing")
}

object Versions {
    const val COROUTINE_VERSION = "1.8.0"
    const val MOCKK_VERSION = "1.12.0"
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE_VERSION}")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testImplementation("io.mockk:mockk:${Versions.MOCKK_VERSION}")
}

publishing {
    publications {
        create("mavenJava", MavenPublication::class) {
            groupId = "com.peacecwz"
            artifactId = "kordinator"
            version = System.getenv("LIBRARY_VERSION") ?: "0.0.1"

            from(components["java"])
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
