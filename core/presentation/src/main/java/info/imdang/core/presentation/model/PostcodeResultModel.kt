package info.imdang.core.presentation.model

data class PostcodeResultModel(
    val zonecode: String,        // 우편번호
    val roadAddress: String,     // 도로명 주소
    val jibunAddress: String,    // 지번 주소
    val extraAddress: String,    // 참고 항목
    val buildingName: String     // 건물명
) {
    val fullAddress: String
        get() = roadAddress + extraAddress

    val isEmpty: Boolean
        get() = zonecode.isEmpty() && roadAddress.isEmpty()
}
