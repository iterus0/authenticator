package xyz.iterus.build.defaults.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class BaseBuildPlugin: Plugin<Project> {

    override fun apply(project: Project) {
        applyPlugins(project)
        applyDependencies(project)
    }

    abstract fun applyPlugins(project: Project)

    abstract fun applyDependencies(project: Project)

}
