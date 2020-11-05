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

dependencies {
    // https://github.com/gradle/kotlin-dsl-samples/issues/1320
    implementation("com.android.tools.build:gradle:4.1.0")
}
