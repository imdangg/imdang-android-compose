package info.imdang.imdang.core.domain.model

data class OnboardingRequestData (
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