import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import xyz.iterus.build.defaults.dependencies.CoreDependencies
import xyz.iterus.build.defaults.dependencies.TestDependencies

plugins {
    kotlin("jvm")
}

val compileKotlin: KotlinCompile by tasks

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
compileKotlin.kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
}

dependencies {
    implementation(CoreDependencies.coroutines_core)

    testImplementation(TestDependencies.junit)
    testImplementation(TestDependencies.koin_test)
    testImplementation(TestDependencies.coroutines_test)
    testImplementation(TestDependencies.mockk)
}
