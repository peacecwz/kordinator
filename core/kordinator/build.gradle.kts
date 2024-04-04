plugins {
    kotlin("jvm")
    id("maven-publish")
    id("signing")
    id("java")
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

java {
    withSourcesJar()
    withJavadocJar()

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "kordinator"
            groupId = "io.github.peacecwz"
            version = project.findProperty("LIBRARY_VERSION")?.toString() ?: "0.1.0"
            description = "Kordinator is simple and lightweight mediator library with Kotlin coroutines supports"

            pom {
                name.set("Kordinator")
                version = project.findProperty("LIBRARY_VERSION")?.toString() ?: "0.1.0"
                description.set("Kordinator is simple and lightweight mediator library with Kotlin coroutines supports")
                url.set("https://github.com/peacecwz/kordinator")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("peacecwz")
                        name.set("Baris Ceviz")
                        email.set("baris@ceviz.dev")
                    }
                }
            }
        }
    }

    repositories {
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

signing {
    useInMemoryPgpKeys(System.getenv("SIGNING_KEY"), System.getenv("SIGNING_PASSWORD"))
    sign(publishing.publications)
}
