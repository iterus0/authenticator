import xyz.iterus.build.defaults.dependencies.*

plugins {
    id("com.android.library")
    id("build.defaults.android")
    id("build.defaults.deps")
    kotlin("kapt")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":features:token"))

    implementation(JetpackDependencies.recyclerview)
    implementation(JetpackDependencies.cardview)

    implementation(MediaDependencies.glide)
    kapt(MediaDependencies.glide_compiler)
}
