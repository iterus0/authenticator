package xyz.iterus.build.defaults.plugins

import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.dependencies
import xyz.iterus.build.defaults.Dependencies

open class KotlinDefaultsPlugin: BaseBuildPlugin() {

    override fun apply(project: Project) {
        super.apply(project)
        project.applyKotlinSources()
    }

    override fun applyPlugins(project: Project) {
        project.applyKotlinPlugins()
    }

    override fun applyDependencies(project: Project) {
        project.applyKotlinDependencies()
    }

}


internal fun Project.applyKotlinSources() {
    val sourceSets = extensions.getByName("sourceSets") as SourceSetContainer

    sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }
}

internal fun Project.applyKotlinPlugins() {
    plugins.apply("org.jetbrains.kotlin.jvm")
}

internal fun Project.applyKotlinDependencies() = dependencies {
    add("implementation", fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    add("implementation", Dependencies.kotlin_std)
    add("implementation", Dependencies.coroutines_core)
    add("implementation", Dependencies.koin_core)
    add("testImplementation", Dependencies.junit)
    add("testImplementation", Dependencies.mockk)
}
