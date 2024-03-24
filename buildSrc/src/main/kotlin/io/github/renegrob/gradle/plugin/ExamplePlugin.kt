package io.github.renegrob.gradle.plugin;

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property

interface ExamplePluginExtension {
    val message: Property<String>
}

class ExamplePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // Add the 'greeting' extension object
        val extension = project.extensions.create("example", ExamplePluginExtension::class.java)
        // Add a task that uses configuration from the extension object
        project.task("example") {
            doLast {
                println(extension.message.get())
            }
        }
    }
}
