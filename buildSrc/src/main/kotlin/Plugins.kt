package xyz.iterus.build.defaults

import org.gradle.api.Project

internal fun Project.configurePlugins() {
    if (project.containsAndroidPlugin()) {
        plugins.apply("org.jetbrains.kotlin.android")
    }
}
