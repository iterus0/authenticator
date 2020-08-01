package xyz.iterus.build.defaults.plugins

import com.android.build.gradle.api.AndroidBasePlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.hasPlugin
import xyz.iterus.build.defaults.Dependencies
import xyz.iterus.build.defaults.applyAndroidConfig

open class AndroidDefaultsPlugin: KotlinDefaultsPlugin() {

    override fun apply(project: Project) {
        if (project.containsAndroidPlugin()) {
            project.applyAndroidConfig()
            super.apply(project)
        } else {
            project.logger.warn("${this@AndroidDefaultsPlugin::class.simpleName} is applied to a non-android module: ${project.name}")
        }
    }

    override fun applyPlugins(project: Project) {
        project.applyAndroidPlugins()
    }

    override fun applyDependencies(project: Project) {
        super.applyDependencies(project)
        project.applyAndroidDependencies()
    }
}


internal fun Project.containsAndroidPlugin(): Boolean = plugins.hasPlugin(AndroidBasePlugin::class)

internal fun Project.applyAndroidPlugins() {
    plugins.apply("org.jetbrains.kotlin.android")
}

internal fun Project.applyAndroidDependencies() = dependencies {
    add("implementation", Dependencies.ktx_core)
    add("implementation", Dependencies.koin_android)
    add("androidTestImplementation", Dependencies.junit_ext)
    add("androidTestImplementation", Dependencies.mockk_android)

    add("implementation", Dependencies.constraintlayout)

    add("implementation", Dependencies.nav_fragment)
    add("implementation", Dependencies.nav_ui)
}
