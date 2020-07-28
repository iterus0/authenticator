import xyz.iterus.build.defaults.Dependencies

plugins {
    id("com.android.application")
    id("build.defaults.android")
}

android {
    defaultConfig {
        applicationId = "xyz.iterus.authenticator"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":ui"))

    implementation(Dependencies.koin_viewmodel)

    debugImplementation(Dependencies.leakcanary)
}
