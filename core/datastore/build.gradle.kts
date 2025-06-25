plugins {
    alias(libs.plugins.imdang.android.library)
    alias(libs.plugins.imdang.hilt)
}

android {
    namespace = "info.imdang.core.datastore"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.common)
    testImplementation(libs.kotlinx.coroutines.test)
}