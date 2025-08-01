package info.imdang.core.presentation.onboarding.enums


enum class OnboardingText(
    val step: OnboardingStep,
    val purpose: UserPurpose,
    val title: OnboardingTextKey,
    val subtitle: OnboardingTextKey,
    val options: List<OptionEnumType> = emptyList()
) {
    /*------------실거주------------*/

    STEP1_REAL(
        step = OnboardingStep.STEP1,
        purpose = UserPurpose.REAL_RESIDENCE,
        title = OnboardingTextKey.STEP1_TITLE,
        subtitle = OnboardingTextKey.STEP1_SUBTITLE,
        options = Step1Options
    ),

    STEP2_REAL(
        step = OnboardingStep.STEP2,
        purpose = UserPurpose.REAL_RESIDENCE,
        title = OnboardingTextKey.STEP2_TITLE,
        subtitle = OnboardingTextKey.STEP2_SUBTITLE,
        options = Step2Options
    ),

    STEP3_REAL(
        step = OnboardingStep.STEP3,
        purpose = UserPurpose.REAL_RESIDENCE,
        title = OnboardingTextKey.STEP3_REAL_TITLE,
        subtitle = OnboardingTextKey.EMPTY,
        options = Step3Options
    ),

    STEP4_REAL(
        step = OnboardingStep.STEP4,
        purpose = UserPurpose.REAL_RESIDENCE,
        title = OnboardingTextKey.STEP4_REAL_TITLE,
        subtitle = OnboardingTextKey.EMPTY,
        options = Step4Options
    ),

    STEP5_REAL(
        step = OnboardingStep.OPT_STEP5,
        purpose = UserPurpose.REAL_RESIDENCE,
        title = OnboardingTextKey.STEP5_TITLE,
        subtitle = OnboardingTextKey.STEP5_SUBTITLE,
        options = listOf(
            PreferenceCategoryType.COMMUTE_AREA,
            PreferenceCategoryType.TRAFFIC,
            PreferenceCategoryType.SCHOOL,
            PreferenceCategoryType.INFRA,
            PreferenceCategoryType.ENVIRONMENT
        )
    ),

    STEP6_REAL(
        step = OnboardingStep.OPT_STEP6,
        purpose = UserPurpose.REAL_RESIDENCE,
        title = OnboardingTextKey.STEP6_TITLE,
        subtitle = OnboardingTextKey.STEP6_SUBTITLE
    ),

    /*------------갭투자------------*/

    STEP1_GAP(
        step = OnboardingStep.STEP1,
        purpose = UserPurpose.GAP_INVESTMENT,
        title = OnboardingTextKey.STEP1_TITLE,
        subtitle = OnboardingTextKey.STEP1_SUBTITLE,
        options = Step1Options
    ),

    STEP2_GAP(
        step = OnboardingStep.STEP2,
        purpose = UserPurpose.GAP_INVESTMENT,
        title = OnboardingTextKey.STEP2_TITLE,
        subtitle = OnboardingTextKey.STEP2_SUBTITLE,
        options = Step2Options
    ),

    STEP3_GAP(
        step = OnboardingStep.STEP3,
        purpose = UserPurpose.GAP_INVESTMENT,
        title = OnboardingTextKey.STEP3_GAP_TITLE,
        subtitle = OnboardingTextKey.EMPTY,
        options = Step3GapOptions
    ),

    STEP4_GAP(
        step = OnboardingStep.STEP4,
        purpose = UserPurpose.GAP_INVESTMENT,
        title = OnboardingTextKey.STEP4_GAP_TITLE,
        subtitle = OnboardingTextKey.EMPTY,
        options = Step4GapOptions
    ),

    STEP5_GAP(
        step = OnboardingStep.OPT_STEP5,
        purpose = UserPurpose.GAP_INVESTMENT,
        title = OnboardingTextKey.STEP5_TITLE,
        subtitle = OnboardingTextKey.STEP5_SUBTITLE,
        options = listOf(
            PreferenceCategoryType.SQUARE_FOOTAGE,
            PreferenceCategoryType.NUM_OF_HOUSEHOLDS,
            PreferenceCategoryType.APT_CATEGORY,
            PreferenceCategoryType.COMMUTE_AREA,
            PreferenceCategoryType.INFRA,
            PreferenceCategoryType.ENVIRONMENT
        )
    ),

    STEP6_GAP(
        step = OnboardingStep.OPT_STEP6,
        purpose = UserPurpose.GAP_INVESTMENT,
        title = OnboardingTextKey.STEP6_TITLE,
        subtitle = OnboardingTextKey.STEP6_SUBTITLE
    )
}

val Step1Options = listOf(
    OptionText.RANGE_1,
    OptionText.RANGE_3,
    OptionText.RANGE_7,
    OptionText.RANGE_9,
    OptionText.RANGE_15,
    OptionText.RANGE_20,
    OptionText.RANGE_30,
    OptionText.RANGE_50
)

val Step2Options = listOf(
    OptionText.NO_INCOME,
    OptionText.INCOME_200,
    OptionText.INCOME_300,
    OptionText.INCOME_400,
    OptionText.INCOME_500,
    OptionText.INCOME_600,
    OptionText.INCOME_700,
    OptionText.INCOME_800,
    OptionText.INCOME_1000
)

val Step3Options = listOf(
    OptionText.NEWLYWEDS,
    OptionText.FAMILY_3_PLUS,
    OptionText.MID_AGE,
    OptionText.SINGLE
)

val Step4Options = listOf(
    OptionText.NO_CHILD,
    OptionText.PLAN_CHILD,
    OptionText.CHILD_1,
    OptionText.CHILD_2,
    OptionText.CHILD_3
)

val Step3GapOptions = listOf(
    OptionText.RATE_90,
    OptionText.RATE_80,
    OptionText.RATE_70,
    OptionText.RATE_60,
    OptionText.RATE_50,
    OptionText.NO_PREFERENCE
)

val Step4GapOptions = listOf(
    OptionText.Y1,
    OptionText.Y2,
    OptionText.Y3,
    OptionText.Y5,
    OptionText.Y10,
    OptionText.Y20,
    OptionText.UNKNOWN
)

interface OptionEnumType

enum class OnboardingTextKey : OptionEnumType {
    STEP1_TITLE,
    STEP1_SUBTITLE,
    STEP2_TITLE,
    STEP2_SUBTITLE,
    STEP3_REAL_TITLE,
    STEP4_REAL_TITLE,
    STEP5_TITLE,
    STEP5_SUBTITLE,
    STEP6_TITLE,
    STEP6_SUBTITLE,
    STEP3_GAP_TITLE,
    STEP4_GAP_TITLE,
    EMPTY
}

enum class OptionText : OptionEnumType {
    RANGE_1, RANGE_3, RANGE_7, RANGE_9, RANGE_15, RANGE_20, RANGE_30, RANGE_50,
    NO_INCOME, INCOME_200, INCOME_300, INCOME_400, INCOME_500, INCOME_600, INCOME_700, INCOME_800, INCOME_1000,
    NEWLYWEDS, FAMILY_3_PLUS, MID_AGE, SINGLE,
    NO_CHILD, PLAN_CHILD, CHILD_1, CHILD_2, CHILD_3,
    RATE_90, RATE_80, RATE_70, RATE_60, RATE_50, NO_PREFERENCE,
    Y1, Y2, Y3, Y5, Y10, Y20, UNKNOWN
}

