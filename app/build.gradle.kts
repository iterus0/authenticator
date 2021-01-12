import xyz.iterus.build.defaults.dependencies.*

plugins {
    id("com.android.application")
    id("build.defaults.android")
    id("build.defaults.deps")
}

android.defaultConfig {
    applicationId = "xyz.iterus.authenticator"
}


dependencies {
    implementation(project(":feature_token"))

    implementation(CoreDependencies.koin_android)

    implementation(JetpackDependencies.appcompat)

    debugImplementation(DebugDependencies.leakcanary)
}
