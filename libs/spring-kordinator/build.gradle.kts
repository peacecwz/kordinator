import com.vanniktech.maven.publish.SonatypeHost

group = "dev.ceviz"

plugins {
    kotlin("jvm")
    id("maven-publish")
    id("signing")
    id("java")
    id("com.vanniktech.maven.publish") version "0.28.0"
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

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// Define sourcesJar task
val sourcesJar = tasks.register("sourcesJar", Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

// Define javadocJar task
val javadocJar = tasks.register("javadocJar", Jar::class) {
    archiveClassifier.set("javadoc")
    from(tasks.named("javadoc"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "dev.ceviz"
            artifactId = "spring-3x-kordinator"
            version = System.getenv("LIBRARY_VERSION") ?: "0.0.1"

            from(components["java"])
            artifact(sourcesJar.get())
            artifact(javadocJar.get())
        }
    }
}

mavenPublishing {
    coordinates("dev.ceviz", "spring-3x-kordinator", "0.0.1")

    pom {
        name.set("Spring 3.x Kordinator")
        description.set("Kordinator is a lightweight library to implement Mediator Pattern with native Coroutine support")
        inceptionYear.set("2024")
        url.set("https://github.com/peacecwz/kordinator")
        licenses {
            license {
                name.set("MIT License")
                url.set("https://www.github.com/peacecwz/kordinator/blob/master/LICENSE")
                distribution.set("https://www.github.com/peacecwz/kordinator/blob/master/LICENSE")
            }
        }
        developers {
            developer {
                id.set("peacecwz")
                name.set("Baris Ceviz")
                url.set("https://github.com/peacecwz")
            }
        }
        scm {
            url.set("https://github.com/peacecwz/kordinator/")
            connection.set("scm:git:git://github.com/peacecwz/kordinator.git")
            developerConnection.set("scm:git:ssh://git@github.com/peacecwz/kordinator.git")
        }
    }
}


afterEvaluate {
    tasks.named("publishMavenPublicationToMavenCentralRepository") {
        mustRunAfter(javadocJar.get())
        mustRunAfter(sourcesJar.get())
        mustRunAfter("signMavenJavaPublication")

    }
    tasks.named("generateMetadataFileForMavenJavaPublication") {
        mustRunAfter("kotlinSourcesJar")
    }

    tasks.named("generateMetadataFileForMavenPublication") {
        mustRunAfter("kotlinSourcesJar")
    }
    tasks.named("generateMetadataFileForMavenPublication") {
        mustRunAfter("plainJavadocJar")
    }
    tasks.named("generateMetadataFileForMavenJavaPublication") {
        mustRunAfter("plainJavadocJar")
    }
    tasks.named("signMavenPublication") {
        mustRunAfter(javadocJar.get())
        mustRunAfter(sourcesJar.get())
    }
    tasks.named("publishMavenJavaPublicationToMavenCentralRepository") {
        mustRunAfter("signMavenPublication")
    }
}

signing {
    useInMemoryPgpKeys(System.getenv("SIGNING_KEY"), System.getenv("SIGNING_PASSWORD"))
    sign(publishing.publications)
}
