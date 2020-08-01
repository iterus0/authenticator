package xyz.iterus.build.defaults

import com.android.build.gradle.BaseExtension
import kotlin.run
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

internal fun Project.applyAndroidConfig() = extensions.getByType<BaseExtension>().run {

    compileSdkVersion(Versions.compile_sdk)

    defaultConfig {
        minSdkVersion(Versions.min_sdk)
        targetSdkVersion(Versions.target_sdk)

        versionCode = Versions.code
        versionName = Versions.name

        testInstrumentationRunner = Dependencies.junit_runner
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            versionNameSuffix = "-debug"
        }

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }
}
