rootProject.name = "kordinator"

include(
    "core:kordinator",
    "libs:spring-kordinator",
    "libs:quarkus-kordinator"
)

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
