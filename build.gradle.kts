plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
    id("io.ktor.plugin") version "3.0.1"
    id("org.jetbrains.kotlinx.rpc.plugin") version "0.4.0"
}

group = "org.mvnsearch"
version = "1.0-SNAPSHOT"

repositories {
    maven(url = "https://maven.pkg.jetbrains.space/public/p/krpc/maven")
    mavenCentral()
}

application {
    mainClass.set("org.mvnsearch.ServerKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(platform("org.jetbrains.kotlinx:kotlinx-rpc-bom:0.4.0"))
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-client")
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-server")
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-serialization-json")

    implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-ktor-client")
    implementation("org.jetbrains.kotlinx:kotlinx-rpc-krpc-ktor-server")

    implementation("io.ktor:ktor-client-cio")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:1.5.12")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    testImplementation("io.ktor:ktor-server-test-host")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}
