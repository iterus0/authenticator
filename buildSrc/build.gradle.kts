plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins.register("build.defaults.android") {
        id = "build.defaults.android"
        implementationClass = "xyz.iterus.build.defaults.AndroidConfigPlugin"
    }
    plugins.register("build.defaults.deps") {
        id = "build.defaults.deps"
        implementationClass = "xyz.iterus.build.defaults.DependenciesPlugin"
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    // https://github.com/gradle/kotlin-dsl-samples/issues/1320
    implementation("com.android.tools.build:gradle:4.2.1")
}
