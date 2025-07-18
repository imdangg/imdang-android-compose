plugins {
    alias(libs.plugins.imdang.hilt)
    alias(libs.plugins.imdang.jvm.library)
    id("com.google.devtools.ksp")
}

dependencies{
    implementation(projects.core.domain)
    implementation(projects.core.common)

    implementation(libs.snadwich)

    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
}