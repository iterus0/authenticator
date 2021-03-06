package xyz.iterus.build.defaults.dependencies

object DebugDependencies {

    private object Versions {
        const val leakcanary = "2.6"
    }

    const val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
}
