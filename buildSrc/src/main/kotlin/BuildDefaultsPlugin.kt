package xyz.iterus.build.defaults

import org.gradle.api.Plugin
import org.gradle.api.Project

open class BuildDefaultsPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.configurePlugins()
        project.configureAndroid()
        project.configureDependencies()
    }
}
