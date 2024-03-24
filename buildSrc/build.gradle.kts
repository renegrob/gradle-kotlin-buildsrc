plugins {
    `kotlin-dsl`
}

repositories {
    // The org.jetbrains.kotlin.jvm plugin requires a repository
    // where to download the Kotlin compiler dependencies from.
    gradlePluginPortal()
    mavenCentral()
}

tasks.validatePlugins {
    enableStricterValidation.set(true)
}

// create the plugin
// Read more: https://docs.gradle.org/current/userguide/implementing_gradle_plugins.html#plugin-development-plugin
gradlePlugin {
    plugins {
        create("examplePlugin") {
            id = "io.github.renegrob.gradle.plugin.example"
            implementationClass = "io.github.renegrob.gradle.plugin.ExamplePlugin"
        }
    }
}