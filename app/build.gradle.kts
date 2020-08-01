import xyz.iterus.build.defaults.Dependencies

plugins {
    id("com.android.application")
    id("build.defaults.android")
}

android.defaultConfig {
    applicationId = "xyz.iterus.authenticator"
}


dependencies {
    implementation(project(":feature_token"))

    implementation(Dependencies.appcompat)

    debugImplementation(Dependencies.leakcanary)
}
