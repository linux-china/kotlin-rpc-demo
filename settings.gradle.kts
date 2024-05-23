rootProject.name = "kotlin-rpc-demo"

pluginManagement {
    repositories {
        maven(url = "https://maven.pkg.jetbrains.space/public/p/krpc/maven")
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}