import xyz.iterus.build.defaults.dependencies.*

plugins {
    id("com.android.library")
    id("build.defaults.android")
    kotlin("kapt")
}

dependencies {
    api(CoreDependencies.kotlin_std)
    api(CoreDependencies.coroutines_core)
    api(CoreDependencies.coroutines_android)

    api(CoreDependencies.koin_core)
    api(CoreDependencies.koin_viewmodel)

    api(JetpackDependencies.lifecycle_viewmodel)
    api(JetpackDependencies.lifecycle_livedata)
    kapt(JetpackDependencies.lifecycle_compiler)

    api(JetpackDependencies.constraintlayout)
    api(JetpackDependencies.nav_fragment)
    api(JetpackDependencies.nav_ui)

    api(JetpackDependencies.ktx_core)
}