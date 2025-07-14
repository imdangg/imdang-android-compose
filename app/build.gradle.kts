import info.imdang.build_logic.ApplicationId
import info.imdang.build_logic.DevConfig
import info.imdang.build_logic.ImdangFlavor
import info.imdang.build_logic.ProductConfig
import info.imdang.build_logic.Release
import info.imdang.build_logic.configureFlavorSettings

plugins {
    alias(libs.plugins.imdang.android.application)
    alias(libs.plugins.imdang.android.application.compose)
    alias(libs.plugins.imdang.android.application.flavors)
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
        debug {
            isDebuggable = true
        }
        release {
            isDebuggable = false
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

    buildTypes {
        debug {
            isDebuggable = true
        }
        release {
            isDebuggable = false
        }
    }

    configureFlavorSettings(this) { flavor ->
        when (flavor) {
            ImdangFlavor.dev -> {
                addManifestPlaceholders(mapOf("KAKAO_NATIVE_KEY" to DevConfig.KAKAO_NATIVE_KEY))
                addManifestPlaceholders(mapOf("NAVER_CLIENT_ID" to DevConfig.NAVER_CLIENT_ID))
                addManifestPlaceholders(mapOf("APP_SCHEME" to DevConfig.APP_SCHEME))
                buildConfigField(
                    "String",
                    "KAKAO_NATIVE_KEY",
                    "\"${DevConfig.KAKAO_NATIVE_KEY}\""
                )
                buildConfigField(
                    "String",
                    "GOOGLE_WEB_CLIENT_ID",
                    "\"${DevConfig.GOOGLE_WEB_CLIENT_ID}\""
                )
                buildConfigField(
                    "String",
                    "NAVER_CLIENT_ID",
                    "\"${DevConfig.NAVER_CLIENT_ID}\""
                )
                buildConfigField(
                    "String",
                    "KAKAO_ADDRESS_SEARCH_SERVER",
                    "\"${DevConfig.KAKAO_ADDRESS_SEARCH_SERVER}\""
                )
                buildConfigField(
                    "String",
                    "APP_SCHEME",
                    "\"${DevConfig.APP_SCHEME}\""
                )
            }

            ImdangFlavor.product -> {
                addManifestPlaceholders(mapOf("KAKAO_NATIVE_KEY" to ProductConfig.KAKAO_NATIVE_KEY))
                addManifestPlaceholders(mapOf("NAVER_CLIENT_ID" to ProductConfig.NAVER_CLIENT_ID))
                addManifestPlaceholders(mapOf("APP_SCHEME" to ProductConfig.APP_SCHEME))
                buildConfigField(
                    "String",
                    "KAKAO_NATIVE_KEY",
                    "\"${ProductConfig.KAKAO_NATIVE_KEY}\""
                )
                buildConfigField(
                    "String",
                    "GOOGLE_WEB_CLIENT_ID",
                    "\"${ProductConfig.GOOGLE_WEB_CLIENT_ID}\""
                )
                buildConfigField(
                    "String",
                    "NAVER_CLIENT_ID",
                    "\"${ProductConfig.NAVER_CLIENT_ID}\""
                )
                buildConfigField(
                    "String",
                    "KAKAO_ADDRESS_SEARCH_SERVER",
                    "\"${DevConfig.KAKAO_ADDRESS_SEARCH_SERVER}\""
                )
                buildConfigField(
                    "String",
                    "KAKAO_NATIVE_KEY",
                    "\"${ProductConfig.KAKAO_NATIVE_KEY}\""
                )
                buildConfigField(
                    "String",
                    "APP_SCHEME",
                    "\"${ProductConfig.APP_SCHEME}\""
                )
            }
        }
    }
}

dependencies {
    implementation(projects.core.component)
    implementation(projects.ui)
    implementation(projects.core.presentation)

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

    implementation(libs.kakao.login)
    implementation(libs.kakao.share)
    implementation(libs.play.services.auth)

    debugImplementation(libs.androidx.compose.ui.testManifest)
    testImplementation(libs.kotlin.test)
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.kotlin.test)
}