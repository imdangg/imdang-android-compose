import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
    id("java-library")
}

group = "info.imdang.imdang.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_11
    }
}
dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("hilt") {
            id = libs.plugins.imdang.hilt.get().pluginId
            implementationClass = "HiltPlugin"
        }
        register("androidApplication") {
            id = libs.plugins.imdang.android.application.get().pluginId
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = libs.plugins.imdang.android.library.get().pluginId
            implementationClass = "AndroidLibraryPlugin"
        }
    }
}