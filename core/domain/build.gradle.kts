plugins {
    alias(libs.plugins.imdang.hilt)
    alias(libs.plugins.imdang.android.library)
    //alias(libs.plugins.imdang.jvm.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "info.imdang.core.domain"
}
dependencies {
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
}