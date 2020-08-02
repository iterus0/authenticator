import xyz.iterus.build.defaults.dependencies.*

plugins {
    id("com.android.library")
    id("build.defaults.android")
    kotlin("kapt")
}

dependencies {
    implementation(project(":library_base"))

    implementation(JetpackDependencies.recyclerview)
    implementation(JetpackDependencies.cardview)

    implementation(MediaDependencies.glide)
    kapt(MediaDependencies.glide_compiler)
}
