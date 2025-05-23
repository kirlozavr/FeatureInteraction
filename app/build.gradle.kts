plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.kirlozavr.featureinteraction"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.kirlozavr.featureinteraction"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(project(":core:base"))

    implementation(project(":core:feature-interaction:api"))
    implementation(project(":core:feature-interaction:impl"))

    implementation(project(":feature:some-feature:api"))
    implementation(project(":feature:some-feature:impl"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}