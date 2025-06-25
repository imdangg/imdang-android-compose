package info.imdang.imdang

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ImdangApplication : Application() {

    override fun onCreate() {
        super.onCreate()

       /*
        TODO
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_KEY)
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient(BuildConfig.NAVER_CLIENT_ID)
       */
    }
}