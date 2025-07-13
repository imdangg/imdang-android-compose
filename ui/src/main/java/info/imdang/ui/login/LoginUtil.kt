package info.imdang.ui.login

import android.content.Context
import android.util.Log
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient

object LoginUtil {

    private const val TAG = "LoginUtil"

    fun startKakaoLogin(
        context: Context,
        onSuccess: (String) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) {
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e(TAG, "카카오계정으로 로그인 실패", error)
                onFailure(error)
            } else if (token != null) {
                Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
                onSuccess(token.accessToken)
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
                if (error != null) {
                    Log.e(TAG, "카카오톡으로 로그인 실패", error)

                    // 사용자가 명시적으로 취소한 경우
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        onFailure(error)
                        return@loginWithKakaoTalk
                    }

                    // 그 외 오류는 계정 로그인으로 fallback
                    UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                } else if (token != null) {
                    Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                    onSuccess(token.accessToken)
                }
            }
        } else {
            // 카카오톡 사용 불가 → 계정 로그인
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
    }
}