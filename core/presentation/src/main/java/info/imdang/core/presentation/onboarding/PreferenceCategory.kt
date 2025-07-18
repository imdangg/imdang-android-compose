package info.imdang.core.presentation.onboarding

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import info.imdang.core.presentation.R

data class DistrictJson(
    val district: String,
    val dong: List<String>
)

sealed class PreferenceCategory(
    open val title: String,
    open val subCategories: List<PreferenceSubCategory> = emptyList(),
    open val options: List<String> = emptyList()
) {
    data class CommuteArea(
        //val purpose: UserPurpose,
        override val title: String,
        override val subCategories: List<PreferenceSubCategory>
    ) : PreferenceCategory(title, subCategories)


    data object Traffic : PreferenceCategory(
        title = TRAFFIC,
        options = listOf("역세권", "버스정류장 인근", "주차 편리", "주차장 엘리베이터 연결", "자차 출퇵느 편리")
    )

    data object School : PreferenceCategory(
        title = SCHOOL,
        options = listOf("초품아", "육아 커뮤니티 활발", "명문 초/중/고", "안전한 통학로", "학원가 근접")

    )

    data object Infra : PreferenceCategory(
        title = INFRA,
        options = listOf("대형마트", "아이동반시설", "대학병원", "문화시설", "백화점")

    )

    data object Environment : PreferenceCategory(
        title = ENVIRONMENT,
        options = listOf("공원", "하천", "등산로", "아파트 밀집", "한강")

    )

    data object SquareFootage : PreferenceCategory(
        title = SQUARE_FOOTAGE,
        options = listOf(
            "초소형(21~40m2)",
            "소형(60m2 이하)",
            "중소형(60~82m2)",
            "중대형(85~102m2)",
            "대형(135m2 이상)"
        )

    )

    data object NumOfHouseHolds : PreferenceCategory(
        title = NUM_OF_HOUSEHOLDS,
        options = listOf("100세대 이상", "500세대 이상", "1000세대 이상", "2000세대 이상", "3000세대 이상")

    )

    data object AptCategory : PreferenceCategory(
        title = APT_CATEGORY,
        options = listOf("신축", "구축")

    )

    companion object {
        val statics: List<PreferenceCategory> = listOf(
            Traffic, School, Infra, Environment, SquareFootage, NumOfHouseHolds, AptCategory
        )

        fun loadFromJson(context: Context/* purpose: UserPurpose*/): PreferenceCategory {
            try {
                val inputStream = context.resources.openRawResource(R.raw.seoul_dong)
                val jsonStr = inputStream.bufferedReader().use { it.readText() }
                Log.d("loadFromJson", "JSON string length: ${jsonStr.length}")
                val gson = Gson()
                val type = object : TypeToken<List<DistrictJson>>() {}.type
                val districts: List<DistrictJson> = gson.fromJson(jsonStr, type)
                Log.d("loadFromJson", "Parsed districts count: ${districts.size}")


                val subCategories = districts.map {
                    PreferenceSubCategory(it.district, it.dong)
                }

                return CommuteArea(
                    //purpose = purpose,
                    title = COMMUTE_AREA,
                    subCategories = subCategories
                )
            } catch (e: Exception) {
                Log.e("loadFromJson", "Error loading json", e)
                throw e
            }
        }

    }

}

data class PreferenceSubCategory(
    val name: String,
    val options: List<String> = emptyList()
)


const val COMMUTE_AREA = "출퇴근 지역"
const val APT_CATEGORY = "유형"
const val NUM_OF_HOUSEHOLDS = "세대수"
const val SQUARE_FOOTAGE = "아파트 형수"
const val ENVIRONMENT = "환경"
const val INFRA = "인프라"
const val SCHOOL = "학군"
const val TRAFFIC = "교통"