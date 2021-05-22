package xyz.iterus.build.defaults

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import kotlin.run
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import xyz.iterus.build.defaults.dependencies.TestDependencies

private object Versions {
    const val name = "0.1.0"
    const val code = 1

    const val compile_sdk = 30
    const val min_sdk = 19
    const val target_sdk = 30
}

internal fun Project.applyAndroidConfig() = extensions.getByType<BaseExtension>().run {

    compileSdkVersion(Versions.compile_sdk)

    defaultConfig {
        minSdkVersion(Versions.min_sdk)
        targetSdkVersion(Versions.target_sdk)

        versionCode = Versions.code
        versionName = Versions.name

        testInstrumentationRunner = TestDependencies.junit_runner
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    sourceSets.all {
        java.srcDir("src/$name/kotlin")
    }
}
