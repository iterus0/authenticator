package xyz.iterus.build.defaults

import com.android.build.gradle.api.AndroidBasePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.hasPlugin

internal class AndroidDefaultsPlugin: Plugin<Project> {

    override fun apply(project: Project) = project.run {
        if (containsAndroidPlugin()) {
            applyAndroidConfig()
            applyAndroidPlugins()
        } else {
            logger.warn("${this@AndroidDefaultsPlugin::class.simpleName} is applied to a non-android module: $name")
        }
    }
}

internal fun Project.containsAndroidPlugin(): Boolean = plugins.hasPlugin(AndroidBasePlugin::class)

internal fun Project.applyAndroidPlugins() {
    plugins.apply("org.jetbrains.kotlin.android")
    plugins.apply("org.jetbrains.kotlin.android.extensions")
}
