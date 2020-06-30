import org.jetbrains.kotlin.config.KotlinCompilerVersion

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "xyz.iterus.authenticator"
        minSdkVersion(19)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "0.1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", KotlinCompilerVersion.VERSION))

    // Concurrency
    val coroutinesVersion = "1.3.0"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // DI / Service locator
    val koinVersion = "2.1.6"
    implementation("org.koin:koin-android:$koinVersion")
    implementation("org.koin:koin-android-viewmodel:$koinVersion")



    // UI
    implementation("androidx.appcompat:appcompat:1.1.0")
    implementation("androidx.core:core-ktx:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")

    val lifecycleVersion = "1.1.1"
    implementation("android.arch.lifecycle:extensions:$lifecycleVersion")
    annotationProcessor("android.arch.lifecycle:compiler:$lifecycleVersion")

    val navVersion = "2.3.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")



    // Debug tools
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.4")



    // Testing
    testImplementation("junit:junit:4.12")
    androidTestImplementation("androidx.test.ext:junit:1.1.1")
    //androidTestImplementation("androidx.test.espresso:espresso-core:3.2.0")

    val mockitoVersion = "3.3.3"
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    androidTestImplementation("org.mockito:mockito-android:$mockitoVersion")
}
