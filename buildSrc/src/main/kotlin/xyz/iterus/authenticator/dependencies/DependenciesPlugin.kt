package xyz.iterus.build.defaults

import kotlin.run
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import xyz.iterus.build.defaults.dependencies.TestDependencies

internal class DependenciesPlugin: Plugin<Project> {

    override fun apply(project: Project) = project.run {
        if (project.parent != null) {
            // apply only to non-root projects
            applyTestDependencies()
        }
    }
}


/*
Can't apply test dependencies in core module,
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
