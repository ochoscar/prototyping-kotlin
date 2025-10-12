plugins {
    kotlin("jvm") version "2.2.20"
    application
}

group = "com.ochoscar"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(22)
}

application {
    mainClass.set("com.ochoscar.MainKt")
}