package xyz.iterus.build.defaults.dependencies

object CoreDependencies {

    internal object Versions {
        const val kotlin = "1.5.31"
        const val koin = "3.1.3"
        const val coroutines = "1.5.2"
    }

    const val koin_core = "io.insert-koin:koin-core:${Versions.koin}"
    const val koin_android = "io.insert-koin:koin-android:${Versions.koin}"
    const val koin_viewmodel = "io.insert-koin:koin-android-viewmodel:${Versions.koin}"

    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}
