import info.imdang.build_logic.DevConfig
import info.imdang.build_logic.ImdangFlavor
import info.imdang.build_logic.ProductConfig
import info.imdang.build_logic.configureFlavorSettings

plugins {
    alias(libs.plugins.imdang.android.library)
    alias(libs.plugins.imdang.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "info.imdang.core.network"

    buildFeatures {
        buildConfig = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("${rootProject.rootDir.absolutePath}/proguard-rules.pro")
            )
        }
    }

    configureFlavorSettings(this) { flavor ->
        when (flavor) {
            ImdangFlavor.dev -> {
                buildConfigField(
                    "String",
                    "API_SERVER",
                    "\"${DevConfig.API_SERVER}\""
                )
            }

            ImdangFlavor.product -> {
                buildConfigField(
                    "String",
                    "API_SERVER",
                    "\"${ProductConfig.API_SERVER}\""
                )
            }
        }
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.common)

    implementation(libs.snadwich.retrofit)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)

    testImplementation(libs.kotlinx.coroutines.test)

}