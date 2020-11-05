package xyz.iterus.build.defaults.dependencies

object JetpackDependencies {

    private object Versions {
        const val appcompat = "1.2.0"
        const val fragment = "1.2.5"
        const val constraintlayout = "2.0.4"
        const val recyclerview = "1.1.0"
        const val cardview = "1.0.0"
        const val lifecycle = "2.2.0"
        const val navigation = "2.3.1"
        const val ktx_core = "1.3.2"
    }

    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardview}"

    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    const val nav_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val nav_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    const val ktx_core = "androidx.core:core-ktx:${Versions.ktx_core}"
}
