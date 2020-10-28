// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

plugins {
    id("com.github.ben-manes.versions") version
        xyz.iterus.build.defaults.dependencies.BuildDependencies.Versions.gradle_versions
}

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        // Can't use imports inside buildScript block:
        // https://github.com/gradle/kotlin-dsl-samples/issues/1291
        classpath(xyz.iterus.build.defaults.dependencies.BuildDependencies.android_gradle_plugin)
        classpath(xyz.iterus.build.defaults.dependencies.BuildDependencies.kotlin_gradle_plugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}


tasks.withType<DependencyUpdatesTask> {
    // reject all non stable versions
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}
