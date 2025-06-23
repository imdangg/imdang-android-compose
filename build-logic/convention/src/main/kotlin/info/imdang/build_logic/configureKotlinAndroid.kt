package info.imdang.build_logic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {

    commonExtension.apply {
        compileSdk = Config.compileSdk

        defaultConfig {
            minSdk = Config.minSdk
        }

        compileOptions {
            sourceCompatibility = Config.javaVersion
            targetCompatibility = Config.javaVersion

        }

        buildTypes{
            getByName("debug"){
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules-debug.pro"
                )
            }

            getByName("release"){
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

    }

}