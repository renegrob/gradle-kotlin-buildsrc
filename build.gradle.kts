import io.github.renegrob.gradle.plugin.CustomCopy

plugins {
    id("java")
    id("io.github.renegrob.gradle.plugin.example")
}

group = "io.github.renegrob.gradle"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:latest")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.test {
    useJUnitPlatform()
}

example {
    message = "Hello there!"
}

tasks.register("myCopy", CustomCopy::class.java) {
    includeFilenames.set(listOf("**/*.kt"))
    shouldCopyWrapper = true
    from(rootDir) {
        include("LICENSE")
        into(".")
    }
    destinationDir = layout.buildDirectory.dir("example").get().asFile
    includeEmptyDirs = false
}
