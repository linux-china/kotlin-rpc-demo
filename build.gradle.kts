plugins {
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.serialization") version "1.9.24"
    id("io.ktor.plugin") version "2.3.11"
    id("com.google.devtools.ksp") version "1.9.24-1.0.20"
    id("org.jetbrains.kotlinx.rpc.plugin") version "0.1.0"
}

group = "org.mvnsearch"
version = "1.0-SNAPSHOT"

repositories {
    maven(url = "https://maven.pkg.jetbrains.space/public/p/krpc/maven")
    mavenCentral()
}

application {
    mainClass.set("ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-runtime-client")
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-runtime-server")
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-runtime-serialization-json")

    implementation("org.jetbrains.kotlinx:kotlinx-rpc-transport-ktor-client")
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-transport-ktor-server")

    implementation("io.ktor:ktor-client-cio-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:1.5.6")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}