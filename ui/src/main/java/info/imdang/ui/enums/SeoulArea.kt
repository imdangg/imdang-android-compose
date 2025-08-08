package info.imdang.ui.enums

import android.content.Context
import info.imdang.ui.R

enum class SeoulArea(val resId: Int) {
    GANGNAM(R.string.location_gangnam_gu),
    GANGDONG(R.string.location_gangdong_gu),
    GANGBUK(R.string.location_gangbuk_gu),
    GANGSEO(R.string.location_gangseo_gu),
    GWANAK(R.string.location_gwanak_gu),
    GWANGJIN(R.string.location_gwangjin_gu),
    GURO(R.string.location_guro_gu),
    GEUMCHEON(R.string.location_geumcheon_gu),
    NOWON(R.string.location_nowon_gu),
    DOBONG(R.string.location_dobong_gu),
    DONGDAEMUN(R.string.location_dongdaemun_gu),
    DONGJAK(R.string.location_dongjak_gu),
    MAPO(R.string.location_mapo_gu),
    SEODAEMUN(R.string.location_seodaemun_gu),
    SEOCHO(R.string.location_seocho_gu),
    SEONGDONG(R.string.location_seongdong_gu),
    SEONGBUK(R.string.location_seongbuk_gu),
    SONGPA(R.string.location_songpa_gu),
    YANGCHEON(R.string.location_yangcheon_gu),
    YEONGDEUNGPO(R.string.location_yeongdeungpo_gu),
    YONGSAN(R.string.location_yongsan_gu),
    EUNPYEONG(R.string.location_eunpyeong_gu),
    JONGNO(R.string.location_jongno_gu),
    JUNG_GU(R.string.location_junggu_gu),
    JUNGRANG(R.string.location_jungrang_gu);

    companion object {

        fun fromString(name: String, context: Context): SeoulArea? =
            entries.find { context.getString(it.resId) == name }
    }
}