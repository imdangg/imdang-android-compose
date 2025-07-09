plugins {
    alias(libs.plugins.imdang.android.library)
    alias(libs.plugins.imdang.android.library.compose)
}

android {
    namespace = "info.imdang.ui"
}

dependencies {
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)
    implementation(projects.core.presentation)
    implementation(projects.core.component)

    //implementation(libs.androidx.core.ktx)
    //androidTestImplementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.hilt.navigation.compose)

}