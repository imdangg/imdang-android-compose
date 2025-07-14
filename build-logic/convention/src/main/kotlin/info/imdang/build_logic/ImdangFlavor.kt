package info.imdang.build_logic

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor

@Suppress("EnumEntryName")
enum class FlavorDimension {
    server
}

@Suppress("EnumEntryName")
enum class ImdangFlavor(val dimension: FlavorDimension, val applicationIdSuffix: String? = null) {
    dev(FlavorDimension.server, applicationIdSuffix = ".dev"),
    product(FlavorDimension.server),
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: ImdangFlavor) -> Unit = {},
) {
    commonExtension.apply {
        FlavorDimension.values().forEach { flavorDimension ->
            flavorDimensions += flavorDimension.name
        }

        productFlavors {
            ImdangFlavor.values().forEach { imdangFlavor ->
                register(imdangFlavor.name) {
                    dimension = imdangFlavor.dimension.name
                    flavorConfigurationBlock(this, imdangFlavor)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (imdangFlavor.applicationIdSuffix != null) {
                            applicationIdSuffix = imdangFlavor.applicationIdSuffix
                        }
                    }
                }
            }
        }
    }
}

// 각 모듈의 build.gradle.kts 파일에서 필요한 configuration을 적용할 수 있도록
fun configureFlavorSettings(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigBlock: ProductFlavor.(flavor: ImdangFlavor) -> Unit
) {
    commonExtension.productFlavors {
        ImdangFlavor.values().forEach { imdangFlavor ->
            named(imdangFlavor.name) {
                flavorConfigBlock(this, imdangFlavor)
            }
        }
    }
}
