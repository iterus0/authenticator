package xyz.iterus.build.defaults

import com.android.build.gradle.api.AndroidBasePlugin
import kotlin.run
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.hasPlugin

internal class AndroidDefaultsPlugin: Plugin<Project> {

    override fun apply(project: Project) = project.run {
        if (containsAndroidPlugin()) {
            applyAndroidConfig()
            applyAndroidPlugins()
            applyTestDependencies()
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

/*
Can't apply test dependencies in library_base,
because there is no testApi() function, like api()
 */
internal fun Project.applyTestDependencies() = dependencies {
    add("testImplementation", Dependencies.junit)
    add("androidTestImplementation", Dependencies.junit_ext)
    add("testImplementation", Dependencies.mockk)
    add("androidTestImplementation", Dependencies.mockk_android)
    add("androidTestImplementation", Dependencies.espresso_core)
}
