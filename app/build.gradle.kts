import info.imdang.build_logic.ApplicationId
import info.imdang.build_logic.Release

plugins {
    alias(libs.plugins.imdang.android.application)
    alias(libs.plugins.imdang.android.application.compose)
    alias(libs.plugins.imdang.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "info.imdang.imdang"

    defaultConfig {
        applicationId = ApplicationId.id
        versionCode = Release.versionCoded
        versionName = Release.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
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

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.core.component)
    implementation(projects.ui)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.adaptive)
    implementation(libs.androidx.compose.material3.adaptive.layout)
    implementation(libs.androidx.compose.material3.adaptive.navigation)
    implementation(libs.androidx.compose.material3.windowSizeClass)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.window.core)
    implementation(libs.kotlinx.serialization.json)

    ksp(libs.hilt.compiler)

    debugImplementation(libs.androidx.compose.ui.testManifest)
    testImplementation(libs.kotlin.test)
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.kotlin.test)
}