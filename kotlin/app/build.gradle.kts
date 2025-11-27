import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "me.brennaswitzer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.7.0")
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib-jdk8"))

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // This dependency is used by the application.
    implementation("com.google.guava:guava:29.0-jre")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("junit:junit:4.12")
    implementation("org.jgrapht:jgrapht-core:1.5.1")
    implementation("org.jgrapht:jgrapht-io:1.5.1")
    implementation("com.github.shiguruikai:combinatoricskt:1.6.0")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}
