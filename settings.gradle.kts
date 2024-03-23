rootProject.name = "kordinator"

include(
    "core:kordinator",
    "libs:spring-kordinator"
)

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}
