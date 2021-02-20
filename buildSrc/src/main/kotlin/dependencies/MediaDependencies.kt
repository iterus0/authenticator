package xyz.iterus.build.defaults.dependencies

object MediaDependencies {

    private object Versions {
        const val glide = "4.12.0"
    }

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}
