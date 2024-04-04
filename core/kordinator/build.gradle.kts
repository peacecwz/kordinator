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
        create<MavenPublication>("mavenJava") {
            groupId = "com.peacecwz"
            artifactId = "kordinator"
            version = project.findProperty("LIBRARY_VERSION")?.toString()?.replace("/", "") ?: "0.0.1"

            from(components["java"])

            pom {
                name.set("Kordinator")
                description.set("A simple coroutine-based coordinator library")
                url.set("https://github.com/peacecwz/kordinator")
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
            url = uri(project.findProperty("REGISTRY_URL") ?: "https://maven.pkg.github.com/peacecwz/kordinator")
            credentials {
                username = project.findProperty("REGISTRY_USERNAME").toString()
                password = project.findProperty("REGISTRY_PASSWORD").toString()
            }
        }
    }
}

signing {
    useInMemoryPgpKeys(System.getenv("SIGNING_KEY"), System.getenv("SIGNING_PASSWORD"))
    sign(publishing.publications)
}
