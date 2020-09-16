plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins.register("build.defaults.android") {
        id = "build.defaults.android"
        implementationClass = "xyz.iterus.build.defaults.AndroidDefaultsPlugin"
    }
}

repositories {
    google()
    jcenter()
}

gradlePlugin {
    plugins.register("build.defaults.android") {
        id = "build.defaults.android"
        implementationClass = "xyz.iterus.build.defaults.AndroidDefaultsPlugin"
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:4.0.1")
}
