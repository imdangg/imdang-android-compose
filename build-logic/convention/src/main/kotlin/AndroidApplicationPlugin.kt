import com.android.build.api.dsl.ApplicationExtension
import info.imdang.build_logic.Config
import info.imdang.build_logic.configureAndroidCompose
import info.imdang.build_logic.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

/*
* hilt + compose
* */
class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            apply(plugin = "com.android.application")
            apply(plugin = "org.jetbrains.kotlin.android")
            apply(plugin = "imdang.hilt")

            val extension = extensions.getByType<ApplicationExtension>()
            extension.apply {
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
                defaultConfig.targetSdk = Config.targetSdk
            }
        }
    }
}