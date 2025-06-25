plugins {
    alias(libs.plugins.imdang.android.library)
    alias(libs.plugins.imdang.android.library.compose)
}

android {
    namespace = "info.imdang.core.ui"
}

dependencies {
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    //implementation(libs.androidx.core.ktx)
    //androidTestImplementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}