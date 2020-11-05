package xyz.iterus.build.defaults.dependencies

object BuildDependencies {

    object Versions {
        const val android_gradle = "4.1.0"
        const val gradle_versions = "0.34.0"
    }

    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.android_gradle}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${CoreDependencies.Versions.kotlin}"
}
