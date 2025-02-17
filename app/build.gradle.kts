plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "st.masoom.videochat"
    compileSdk = 35

    defaultConfig {
        applicationId = "st.masoom.videochat"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("io.agora.rtc:full-sdk:3.5.1")
    //implementation (libs.android.uikit)
    implementation ("com.github.AgoraIO-Community:Android-UIKit:v2.0.0")


    // Compose
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")
    implementation ("androidx.navigation:navigation-compose:2.8.7")
    implementation (libs.androidx.activity.compose.v140)

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1")

    // Coroutine Lifecycle Scopes
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
}