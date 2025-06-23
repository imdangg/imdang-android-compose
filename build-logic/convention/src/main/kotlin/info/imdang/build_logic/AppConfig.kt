package info.imdang.build_logic

import org.gradle.api.JavaVersion

object ApplicationId {
    val id = "info.imdang.imdang"
}

object Release {
    val versionCoded = 1
    val versionName = "1.0.0"
}

object Config{
    const val compileSdk = 35
    const val targetSdk = 34
    const val minSdk = 29

    const val jvmTarget ="17"
    val javaVersion = JavaVersion.VERSION_17
}

/*
* 기존 xml 임당
* object AppConfig {
    const val COMPILE_SDK = 34
    const val MIN_SDK = 29
    const val TARGET_SDK = 34
    const val VERSION_CODE = 9
    const val VERSION_NAME = "1.0.0"
}
* */