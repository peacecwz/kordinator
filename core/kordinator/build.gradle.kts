plugins {
    kotlin("jvm")
}

object Versions {
    const val COROUTINE_VERSION = "1.8.0"
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINE_VERSION}")

}
