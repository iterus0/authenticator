package xyz.iterus.build.defaults

import com.android.build.gradle.api.AndroidBasePlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

object Dependencies {
    const val kotlin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk7"

    const val koin_core = "org.koin:koin-core:${Versions.koin}"
    const val koin_android = "org.koin:koin-android:${Versions.koin}"
    const val koin_viewmodel = "org.koin:koin-android-viewmodel:${Versions.koin}"

    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val junit = "junit:junit:${Versions.junit}"
    const val junit_ext = "androidx.test.ext:junit:${Versions.junit_ext}"
    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockito_android = "org.mockito:mockito-android:${Versions.mockito}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val nav_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigaton}"
    const val nav_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigaton}"
    const val lifecycle_ext = "android.arch.lifecycle:extensions:${Versions.lifecycle}"
    const val lifecycle_compiler = "android.arch.lifecycle:compiler:${Versions.lifecycle}"
    const val ktx_core = "androidx.core:core-ktx:${Versions.ktx_core}"

    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
}


/***
 * Applies default dependencies for every module in the project
 */
internal fun Project.configureDependencies() = dependencies {
    add("implementation", fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    add("implementation", Dependencies.kotlin_std)
    add("testImplementation", Dependencies.junit)
    add("testImplementation", Dependencies.mockito_core)

    if (project.containsAndroidPlugin()) {
        add("implementation", Dependencies.ktx_core)
        add("implementation", Dependencies.koin_android)
        add("androidTestImplementation", Dependencies.junit_ext)
        add("androidTestImplementation", Dependencies.mockito_android)
    } else {
        add("implementation", Dependencies.koin_core)
    }
}

internal fun Project.containsAndroidPlugin(): Boolean {
    return project.plugins.toList().any { plugin -> plugin is AndroidBasePlugin }
}
