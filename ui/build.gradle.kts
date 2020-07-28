import xyz.iterus.build.defaults.Dependencies

plugins {
    id("com.android.library")
    id("build.defaults")
    kotlin("kapt")
    kotlin("android.extensions")
}

dependencies {
    implementation(project(":core"))

    implementation(Dependencies.coroutines_android)
    implementation(Dependencies.koin_viewmodel)

    implementation(Dependencies.lifecycle_ext)
    kapt(Dependencies.lifecycle_compiler)

    implementation(Dependencies.appcompat)
    implementation(Dependencies.constraintlayout)
    implementation(Dependencies.recyclerview)
    implementation(Dependencies.cardview)

    implementation(Dependencies.glide)
    kapt(Dependencies.glide_compiler)

    implementation(Dependencies.nav_fragment)
    implementation(Dependencies.nav_ui)

    androidTestImplementation(Dependencies.espresso_core)
}
