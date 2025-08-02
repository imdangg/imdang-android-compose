package info.imdang.imdang.core.data.datasource.model

import info.imdang.imdang.core.domain.model.OnboardingRequestData

data class OnboardingRequestEntity(
    val purpose: String,
    val budget: String,
    val monthIncome: String,
    val livingPerson: String?,
    val childrenPlan: String?,
    val hopeGap: String?,
    val investmentPlan: String?,
    val traffic: String?,
    val schoolDistrict: String?,
    val apartmentSquare: String?,
    val household: String?,
    val houseType: String?,
    val commutingArea: String?,
    val infra: String?,
    val environment: String?,
    val firstPriority: String?,
    val secondPriority: String?,
    val thirdPriority: String?,
    val interestDistrict: String?
)

fun OnboardingRequestData.toData() = OnboardingRequestEntity(
    purpose,
    budget,
    monthIncome,
    livingPerson,
    childrenPlan,
    hopeGap,
    investmentPlan,
    traffic,
    schoolDistrict,
    apartmentSquare,
    household,
    houseType,
    commutingArea,
    infra,
    environment,
    firstPriority,
    secondPriority,
    thirdPriority,
    interestDistrict
)

