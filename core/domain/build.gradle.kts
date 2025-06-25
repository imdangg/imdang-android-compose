plugins {
    alias(libs.plugins.imdang.android.library)
    alias(libs.plugins.imdang.hilt)
}
android{
    namespace = "info.imdang.core.domain"
}
dependencies {
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
}