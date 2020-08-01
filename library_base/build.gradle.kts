import xyz.iterus.build.defaults.Dependencies

plugins {
    id("com.android.library")
    id("build.defaults.android")
    kotlin("kapt")
}

dependencies {
    api(Dependencies.kotlin_std)
    api(Dependencies.coroutines_core)
    api(Dependencies.coroutines_android)

    api(Dependencies.koin_core)
    api(Dependencies.koin_viewmodel)

    testApi(Dependencies.junit)
    androidTestApi(Dependencies.junit_ext)
    testApi(Dependencies.mockk)
    androidTestApi(Dependencies.mockk_android)
    androidTestApi(Dependencies.espresso_core)

    api(Dependencies.lifecycle_viewmodel)
    api(Dependencies.lifecycle_livedata)
    kapt(Dependencies.lifecycle_compiler)

    api(Dependencies.constraintlayout)
    api(Dependencies.nav_fragment)
    api(Dependencies.nav_ui)

    api(Dependencies.ktx_core)
}
