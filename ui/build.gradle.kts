import xyz.iterus.build.defaults.Dependencies

plugins {
    id("com.android.library")
    id("build.defaults")
    kotlin("kapt")
    kotlin("android.extensions")
}

dependencies {
    implementation(project(":core"))

    implementation(Dependencies.coroutines_core)
    implementation(Dependencies.coroutines_android)
    implementation(Dependencies.koin_viewmodel)

    implementation(Dependencies.lifecycle_viewmodel)
    implementation(Dependencies.lifecycle_livedata)
    kapt(Dependencies.lifecycle_compiler)

    implementation(Dependencies.appcompat)
    implementation(Dependencies.constraintlayout)

    implementation(Dependencies.nav_fragment)
    implementation(Dependencies.nav_ui)

    androidTestImplementation(Dependencies.espresso_core)
}
