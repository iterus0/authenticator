import xyz.iterus.build.defaults.Dependencies

plugins {
    id("com.android.library")
    id("build.defaults.android")
    kotlin("android.extensions")
    kotlin("kapt")
}

dependencies {
    implementation(Dependencies.coroutines_android)
    implementation(Dependencies.koin_viewmodel)

    implementation(Dependencies.lifecycle_viewmodel)
    implementation(Dependencies.lifecycle_livedata)
    kapt(Dependencies.lifecycle_compiler)

    implementation(Dependencies.recyclerview)
    implementation(Dependencies.cardview)

    implementation(Dependencies.glide)
    kapt(Dependencies.glide_compiler)


    androidTestImplementation(Dependencies.espresso_core)
}
