package io.github.renegrob.gradle.plugin

import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.util.PatternSet

abstract class CustomCopy() : Copy() {

    @get:Input
    val includeFilenames: ListProperty<String> = project.objects.listProperty(String::class.java)

    @get:Input
    val shouldCopyWrapper: Property<Boolean> = project.objects.property(Boolean::class.java)


    init {
        mainSpec.from("buildSrc") {
            into("special")
            include {
                PatternSet().include(includeFilenames.get()).asSpec.isSatisfiedBy(it)
            }
        }
        mainSpec.from(project.rootDir) {
            into("wrapper")
            include {
                if (shouldCopyWrapper.get()) PatternSet().include("gradlew", "gradlew.bat").asSpec.isSatisfiedBy(it) else false
            }
        }
        mainSpec.from("gradle") {
            into("/wrapper/gradle")
            include {
                shouldCopyWrapper.get()
            }
        }
    }
}