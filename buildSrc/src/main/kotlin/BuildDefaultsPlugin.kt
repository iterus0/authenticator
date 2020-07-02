package xyz.iterus.build.defaults

import com.android.build.gradle.api.AndroidBasePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

open class BuildDefaultsPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.configurePlugins()
        if (project.containsAndroidPlugin()) {
            project.configureAndroid()
        }
        project.configureDependencies()
    }
}

internal fun Project.containsAndroidPlugin(): Boolean {
    return project.plugins.toList().any { plugin -> plugin is AndroidBasePlugin }
}
