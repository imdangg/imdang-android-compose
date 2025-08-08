package info.imdang.ui.enums

import android.content.Context
import info.imdang.ui.R

enum class SeoulArea(val resId: Int) {
    GANGNAM(R.string.location_gangnam),
    GANGDONG(R.string.location_gangdong),
    GANGBUK(R.string.location_gangbuk),
    GANGSEO(R.string.location_gangseo),
    GWANAK(R.string.location_gwanak),
    GWANGJIN(R.string.location_gwangjin),
    GURO(R.string.location_guro),
    GEUMCHEON(R.string.location_geumcheon),
    NOWON(R.string.location_nowon),
    DOBONG(R.string.location_dobong),
    DONGDAEMUN(R.string.location_dongdaemun),
    DONGJAK(R.string.location_dongjak),
    MAPO(R.string.location_mapo),
    SEODAEMUN(R.string.location_seodaemun),
    SEOCHO(R.string.location_seocho),
    SEONGDONG(R.string.location_seongdong),
    SEONGBUK(R.string.location_seongbuk),
    SONGPA(R.string.location_songpa),
    YANGCHEON(R.string.location_yangcheon),
    YEONGDEUNGPO(R.string.location_yeongdeungpo),
    YONGSAN(R.string.location_yongsan),
    EUNPYEONG(R.string.location_eunpyeong),
    JONGNO(R.string.location_jongno),
    JUNG_GU(R.string.location_junggu),
    JUNGRANG(R.string.location_jungrang);

    companion object {

        fun fromString(name: String, context: Context): SeoulArea? =
            entries.find { context.getString(it.resId) == name }
    }
}