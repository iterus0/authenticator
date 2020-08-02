package xyz.iterus.build.defaults.dependencies

object TestDependencies {

    private object Versions {
        const val junit = "4.13"
        const val junit_ext = "1.1.1"
        const val mockk = "1.10.0"
        const val espresso = "3.2.0"
    }

    const val junit = "junit:junit:${Versions.junit}"
    const val junit_ext = "androidx.test.ext:junit:${Versions.junit_ext}"
    const val junit_runner = "androidx.test.runner.AndroidJUnitRunner"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val mockk_android = "io.mockk:mockk-android:${Versions.mockk}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
