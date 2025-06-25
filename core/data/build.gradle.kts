plugins {
    alias(libs.plugins.imdang.android.library)
    alias(libs.plugins.imdang.hilt)
}
android {
    namespace = "info.imdang.core.data"
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}
dependencies{
    implementation(projects.core.domain)

}