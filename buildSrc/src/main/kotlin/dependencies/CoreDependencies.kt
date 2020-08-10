package xyz.iterus.build.defaults.dependencies

object CoreDependencies {

    internal object Versions {
        const val koin = "2.1.6"
        const val coroutines = "1.3.8"
    }

    const val kotlin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk7"

    const val koin_core = "org.koin:koin-core:${Versions.koin}"
    const val koin_android = "org.koin:koin-android:${Versions.koin}"
    const val koin_viewmodel = "org.koin:koin-android-viewmodel:${Versions.koin}"

    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}
