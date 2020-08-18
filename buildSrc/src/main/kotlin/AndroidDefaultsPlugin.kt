package xyz.iterus.build.defaults

import com.android.build.gradle.api.AndroidBasePlugin
import kotlin.run
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.hasPlugin
import org.gradle.kotlin.dsl.project
import xyz.iterus.build.defaults.dependencies.TestDependencies

internal class AndroidDefaultsPlugin: Plugin<Project> {

    override fun apply(project: Project) = project.run {
        if (containsAndroidPlugin()) {
            applyAndroidConfig()
            applyAndroidPlugins()
            applyBaseDependencies()
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

internal fun Project.applyBaseDependencies() = dependencies {
    if (!name.contains("library")) {
        add("implementation", dependencies.project(":library_base"))
    }
}

/*
Can't apply test dependencies in library_base,
because there is no testApi() function, like api()
 */
internal fun Project.applyTestDependencies() = dependencies {
    add("testImplementation", TestDependencies.junit)
    add("androidTestImplementation", TestDependencies.junit_ext)
    add("testImplementation", TestDependencies.koin_test)
    add("testImplementation", TestDependencies.coroutines_test)
    add("testImplementation", TestDependencies.mockk)
    add("androidTestImplementation", TestDependencies.mockk_android)
    add("androidTestImplementation", TestDependencies.espresso_core)
}
