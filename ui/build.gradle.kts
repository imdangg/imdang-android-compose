import info.imdang.build_logic.DevConfig
import info.imdang.build_logic.ImdangFlavor
import info.imdang.build_logic.ProductConfig
import info.imdang.build_logic.configureFlavorSettings

plugins {
    alias(libs.plugins.imdang.android.library)
    alias(libs.plugins.imdang.android.library.compose)
    alias(libs.plugins.imdang.hilt)
    alias(libs.plugins.imdang.android.firebase)
}

android {
    namespace = "info.imdang.ui"

    buildFeatures {
        buildConfig = true
    }

    configureFlavorSettings(this) { flavor ->
        when (flavor) {
            ImdangFlavor.dev -> {
                buildConfigField(
                    "String",
                    "GOOGLE_WEB_CLIENT_ID",
                    "\"${DevConfig.GOOGLE_WEB_CLIENT_ID}\""
                )
            }

            ImdangFlavor.product -> {
                buildConfigField(
                    "String",
                    "GOOGLE_WEB_CLIENT_ID",
                    "\"${ProductConfig.GOOGLE_WEB_CLIENT_ID}\""
                )
            }
        }
    }
}

dependencies {
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(projects.core.presentation)
    implementation(projects.core.component)
    implementation(libs.androidx.ui.test.junit4.android)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.kakao.login)
    implementation(libs.kakao.share)

    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.auth)
    implementation(libs.google.identity.googleid)

    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.lottie.compose)
}