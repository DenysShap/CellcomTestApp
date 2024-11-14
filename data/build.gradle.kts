plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")

        ksp {
            arg("room.schemaLocation", "$rootDir/app/schemas/")
        }

        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", "\"2c46288716a18fb7aadcc2a801f3fc6b\"")
        /*buildConfigField("String", "API_KEY", "\"10beccb5fc0344c2ea40212bd521226d\"")*/
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
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.androidx.paging.common.android)

    implementation(libs.androidx.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    implementation(project(":domain"))
}