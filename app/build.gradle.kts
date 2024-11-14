plugins {
    kotlin("kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.androidHilt)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinParcelize)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.cellcomtestapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.cellcomtestapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "SMALL_IMAGE_URL", "\"https://image.tmdb.org/t/p/w200\"")
        buildConfigField("String", "LARGE_IMAGE_URL", "\"https://image.tmdb.org/t/p/w500\"")
        buildConfigField("String", "ORIGINAL_IMAGE_URL", "\"https://image.tmdb.org/t/p/original\"")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.paging.compose.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    implementation(libs.coil.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.paging.common)
    implementation(libs.androidx.paging.runtime.ktx)

    implementation(libs.androidx.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    implementation(libs.youtube.player)

    implementation(project(":data"))
    implementation(project(":domain"))
}