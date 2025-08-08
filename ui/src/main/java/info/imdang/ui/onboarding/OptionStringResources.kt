package info.imdang.ui.onboarding

import android.content.Context
import info.imdang.core.presentation.onboarding.enums.OnboardingTextKey
import info.imdang.core.presentation.onboarding.enums.OptionEnumType
import info.imdang.core.presentation.onboarding.enums.OptionText
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import info.imdang.core.presentation.onboarding.enums.AptCategoryOption
import info.imdang.core.presentation.onboarding.enums.CommuteAreaOption
import info.imdang.core.presentation.onboarding.enums.EnvironmentOption
import info.imdang.core.presentation.onboarding.enums.InfraOption
import info.imdang.core.presentation.onboarding.enums.NumOfHouseholdsOption
import info.imdang.core.presentation.onboarding.enums.PreferenceCategoryType
import info.imdang.core.presentation.onboarding.enums.SchoolOption
import info.imdang.core.presentation.onboarding.enums.SquareFootageOption
import info.imdang.core.presentation.onboarding.enums.TrafficOption
import info.imdang.ui.R

//온보딩 우선 순위 선택 페이지 tab 이름(category)
fun PreferenceCategoryType.labelResId(): Int = when (this) {
    PreferenceCategoryType.COMMUTE_AREA -> R.string.pref_commute_area
    PreferenceCategoryType.TRAFFIC -> R.string.pref_traffic
    PreferenceCategoryType.SCHOOL -> R.string.pref_school
    PreferenceCategoryType.INFRA -> R.string.pref_infra
    PreferenceCategoryType.ENVIRONMENT -> R.string.pref_environment
    PreferenceCategoryType.SQUARE_FOOTAGE -> R.string.pref_square_footage
    PreferenceCategoryType.NUM_OF_HOUSEHOLDS -> R.string.pref_num_of_households
    PreferenceCategoryType.APT_CATEGORY -> R.string.pref_apt_category
}

//온보딩 우선 순위 선택 페이지 tab(category) 내부 options
fun PreferenceCategoryType.getOptionResId(option: Any?): Int = when (this) {
    PreferenceCategoryType.TRAFFIC -> (option as TrafficOption).labelResId()
    PreferenceCategoryType.SCHOOL -> (option as SchoolOption).labelResId()
    PreferenceCategoryType.INFRA -> (option as InfraOption).labelResId()
    PreferenceCategoryType.ENVIRONMENT -> (option as EnvironmentOption).labelResId()
    PreferenceCategoryType.SQUARE_FOOTAGE -> (option as SquareFootageOption).labelResId()
    PreferenceCategoryType.NUM_OF_HOUSEHOLDS -> (option as NumOfHouseholdsOption).labelResId()
    PreferenceCategoryType.APT_CATEGORY -> (option as AptCategoryOption).labelResId()
    PreferenceCategoryType.COMMUTE_AREA -> (option as CommuteAreaOption).labelResId()
}
fun CommuteAreaOption.labelResId(): Int = when (this) {
    CommuteAreaOption.GANGNAM -> R.string.location_gangnam
    CommuteAreaOption.YEOUIDO -> R.string.location_yeouido
    CommuteAreaOption.GWANGHWAMUN -> R.string.location_gwanghwamun
    CommuteAreaOption.EULJIRO -> R.string.location_euljiro
    CommuteAreaOption.SEONGSU -> R.string.location_seongsu
    CommuteAreaOption.PANGYO -> R.string.location_pangyo
    CommuteAreaOption.MAPO -> R.string.location_mapo
    CommuteAreaOption.MAGOK -> R.string.location_magok
    CommuteAreaOption.GURO -> R.string.location_guro
    CommuteAreaOption.SANGAMDMC -> R.string.location_sangam_dmc
    CommuteAreaOption.GANGSEO -> R.string.location_gangseo
    CommuteAreaOption.SONGPA -> R.string.location_songpa
}

fun TrafficOption.labelResId(): Int = when (this) {
    TrafficOption.NEAR_SUBWAY -> R.string.option_traffic_near_subway
    TrafficOption.BUS_STOP -> R.string.option_traffic_bus_stop
    TrafficOption.EASY_PARKING -> R.string.option_traffic_easy_parking
    TrafficOption.ELEVATOR_LINK -> R.string.option_traffic_elevator_link
    TrafficOption.CAR_COMMUTE -> R.string.option_traffic_car_commute
}

fun SchoolOption.labelResId(): Int = when (this) {
    SchoolOption.NEAR_SCHOOL -> R.string.option_school_near
    SchoolOption.ACTIVE_COMMUNITY -> R.string.option_school_community
    SchoolOption.ELITE_SCHOOL -> R.string.option_school_elite
    SchoolOption.SAFE_ROAD -> R.string.option_school_safe_road
    SchoolOption.NEAR_ACADEMY -> R.string.option_school_academy_zone
}

fun InfraOption.labelResId(): Int = when (this) {
    InfraOption.BIG_MART -> R.string.option_infra_mart
    InfraOption.KID_FACILITY -> R.string.option_infra_kid_facility
    InfraOption.UNIVERSITY_HOSPITAL -> R.string.option_infra_university_hospital
    InfraOption.CULTURE_CENTER -> R.string.option_infra_culture
    InfraOption.DEPARTMENT_STORE -> R.string.option_infra_department_store
}

fun EnvironmentOption.labelResId(): Int = when (this) {
    EnvironmentOption.PARK -> R.string.option_env_park
    EnvironmentOption.STREAM -> R.string.option_env_stream
    EnvironmentOption.MOUNTAIN -> R.string.option_env_mountain
    EnvironmentOption.APT_DENSE -> R.string.option_env_apartment_dense
    EnvironmentOption.HANGANG -> R.string.option_env_hangang
}

fun SquareFootageOption.labelResId(): Int = when (this) {
    SquareFootageOption.VERY_SMALL -> R.string.option_sqft_very_small
    SquareFootageOption.SMALL -> R.string.option_sqft_small
    SquareFootageOption.MID_SMALL -> R.string.option_sqft_mid_small
    SquareFootageOption.MID_LARGE -> R.string.option_sqft_mid_large
    SquareFootageOption.LARGE -> R.string.option_sqft_large
}

fun NumOfHouseholdsOption.labelResId(): Int = when (this) {
    NumOfHouseholdsOption.OVER_100 -> R.string.option_household_100
    NumOfHouseholdsOption.OVER_500 -> R.string.option_household_500
    NumOfHouseholdsOption.OVER_1000 -> R.string.option_household_1000
    NumOfHouseholdsOption.OVER_2000 -> R.string.option_household_2000
    NumOfHouseholdsOption.OVER_3000 -> R.string.option_household_3000
}

fun AptCategoryOption.labelResId(): Int = when (this) {
    AptCategoryOption.NEW -> R.string.option_apt_new
    AptCategoryOption.OLD -> R.string.option_apt_old
}


//온보딩 STEP1TO4 UI 의 선택 options
fun OptionEnumType.asString(context: Context): String {
    val resId = when (this) {
        OptionText.RANGE_1 -> R.string.range_1
        OptionText.RANGE_3 -> R.string.range_3
        OptionText.RANGE_7 -> R.string.range_7
        OptionText.RANGE_9 -> R.string.range_9
        OptionText.RANGE_15 -> R.string.range_15
        OptionText.RANGE_20 -> R.string.range_20
        OptionText.RANGE_30 -> R.string.range_30
        OptionText.RANGE_50 -> R.string.range_50

        OptionText.NO_INCOME -> R.string.no_income
        OptionText.INCOME_200 -> R.string.income_200
        OptionText.INCOME_300 -> R.string.income_300
        OptionText.INCOME_400 -> R.string.income_400
        OptionText.INCOME_500 -> R.string.income_500
        OptionText.INCOME_600 -> R.string.income_600
        OptionText.INCOME_700 -> R.string.income_700
        OptionText.INCOME_800 -> R.string.income_800
        OptionText.INCOME_1000 -> R.string.income_1000

        OptionText.NEWLYWEDS -> R.string.newlyweds
        OptionText.FAMILY_3_PLUS -> R.string.family_3_plus
        OptionText.MID_AGE -> R.string.mid_age
        OptionText.SINGLE -> R.string.single

        OptionText.NO_CHILD -> R.string.no_child
        OptionText.PLAN_CHILD -> R.string.plan_child
        OptionText.CHILD_1 -> R.string.child_1
        OptionText.CHILD_2 -> R.string.child_2
        OptionText.CHILD_3 -> R.string.child_3

        OptionText.RATE_20 -> R.string.rate_20
        OptionText.RATE_30 -> R.string.rate_30
        OptionText.RATE_40 -> R.string.rate_40
        OptionText.RATE_50 -> R.string.rate_50
        OptionText.RATE_60 -> R.string.rate_60
        OptionText.NO_PREFERENCE -> R.string.no_preference

        OptionText.Y1 -> R.string.y1
        OptionText.Y2 -> R.string.y2
        OptionText.Y3 -> R.string.y3
        OptionText.Y5 -> R.string.y5
        OptionText.Y10 -> R.string.y10
        OptionText.Y20 -> R.string.y20
        OptionText.UNKNOWN -> R.string.unknown
        else -> error("Unhandled OptionEnumType")
    }
    return context.getString(resId)
}
//온보딩 UI 의 title/subtitle
@Composable
fun OnboardingTextKey.asString(): String {
    return stringResource(
        id = when (this) {
            OnboardingTextKey.STEP1_TITLE -> R.string.step1_title
            OnboardingTextKey.STEP1_SUBTITLE -> R.string.step1_subtitle
            OnboardingTextKey.STEP2_TITLE -> R.string.step2_title
            OnboardingTextKey.STEP2_SUBTITLE -> R.string.step2_subtitle
            OnboardingTextKey.STEP3_REAL_TITLE -> R.string.step3_real_title
            OnboardingTextKey.STEP4_REAL_TITLE -> R.string.step4_real_title
            OnboardingTextKey.STEP5_TITLE -> R.string.step5_title
            OnboardingTextKey.STEP5_SUBTITLE -> R.string.step5_subtitle
            OnboardingTextKey.STEP6_TITLE -> R.string.step6_title
            OnboardingTextKey.STEP6_SUBTITLE -> R.string.step6_subtitle
            OnboardingTextKey.STEP3_GAP_TITLE -> R.string.step3_gap_title
            OnboardingTextKey.STEP4_GAP_TITLE -> R.string.step4_gap_title
            OnboardingTextKey.EMPTY -> R.string.empty
        }
    )
}

