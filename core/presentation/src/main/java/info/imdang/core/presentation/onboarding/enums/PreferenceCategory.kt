package info.imdang.core.presentation.onboarding.enums


sealed class PreferenceCategory<T>(
    open val type: PreferenceCategoryType,
    open val subCategories: List<PreferenceSubCategory> = emptyList(),
    open val options: List<T> = emptyList()
) {

    data class CommuteArea(
        override val type: PreferenceCategoryType = PreferenceCategoryType.COMMUTE_AREA,
        override val subCategories: List<PreferenceSubCategory>
    ) : PreferenceCategory<Nothing>(
        type = type,
        subCategories = subCategories
    )
    data object Traffic : PreferenceCategory<TrafficOption>(
        type = PreferenceCategoryType.TRAFFIC,
        options = TrafficOption.entries
    )

    data object School : PreferenceCategory<SchoolOption>(
        type = PreferenceCategoryType.SCHOOL,
        options = SchoolOption.entries
    )

    data object Infra : PreferenceCategory<InfraOption>(
        type = PreferenceCategoryType.INFRA,
        options = InfraOption.entries
    )

    data object Environment : PreferenceCategory<EnvironmentOption>(
        type = PreferenceCategoryType.ENVIRONMENT,
        options = EnvironmentOption.entries
    )

    data object SquareFootage : PreferenceCategory<SquareFootageOption>(
        type = PreferenceCategoryType.SQUARE_FOOTAGE,
        options = SquareFootageOption.entries
    )

    data object NumOfHouseHolds : PreferenceCategory<NumOfHouseholdsOption>(
        type = PreferenceCategoryType.NUM_OF_HOUSEHOLDS,
        options = NumOfHouseholdsOption.entries
    )

    data object AptCategory : PreferenceCategory<AptCategoryOption>(
        type = PreferenceCategoryType.APT_CATEGORY,
        options = AptCategoryOption.entries
    )


    companion object {

        val statics: List<PreferenceCategory<*>> = listOf(
           Traffic, School, Infra, Environment, SquareFootage, NumOfHouseHolds, AptCategory
        )
    }

}

data class PreferenceSubCategory(
    val name: String,
    val options: List<String> = emptyList()
)