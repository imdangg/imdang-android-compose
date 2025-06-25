plugins {
    alias(libs.plugins.imdang.jvm.library)
    alias(libs.plugins.kotlin.jvm)
}

dependencies{
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
}