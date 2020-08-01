import xyz.iterus.build.defaults.Dependencies

plugins {
    id("com.android.library")
    id("build.defaults.android")
    kotlin("android.extensions")
    kotlin("kapt")
}

dependencies {
    implementation(project(":library_base"))

    implementation(Dependencies.recyclerview)
    implementation(Dependencies.cardview)

    implementation(Dependencies.glide)
    kapt(Dependencies.glide_compiler)
}
