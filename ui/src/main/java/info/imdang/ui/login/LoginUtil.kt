package info.imdang.ui.login

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import info.imdang.ui.BuildConfig

object LoginUtil {

    private const val TAG = "LoginUtil"

    suspend fun startLogin(
        platform: LoginPlatform,
        context: Context,
        onSuccess: (String) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) {
        when (platform) {
            LoginPlatform.KAKAO -> {
                startKakaoLogin(context, onSuccess, onFailure)
            }

            LoginPlatform.GOOGLE -> {
                startGoogleLogin(context, onSuccess, onFailure)
            }
        }
    }

    private fun startKakaoLogin(
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

    private suspend fun startGoogleLogin(
        context: Context,
        onSuccess: (String) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) {
        val credentialManager = CredentialManager.create(context)
        val googleOption = GetSignInWithGoogleOption.Builder(BuildConfig.GOOGLE_WEB_CLIENT_ID)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleOption)
            .build()

        try {
            val result = credentialManager.getCredential(context, request)
            handleGoogleCredential(result, onSuccess, onFailure)
        } catch (e: GetCredentialException) {
            Log.e(TAG, "Google 로그인 실패", e)
            onFailure(e)
        }
    }

    private fun handleGoogleCredential(
        result: GetCredentialResponse,
        onSuccess: (String) -> Unit,
        onFailure: (Throwable?) -> Unit
    ) {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleCredential = GoogleIdTokenCredential.createFrom(credential.data)
                        val idToken = googleCredential.idToken
                        Log.d(TAG, "Google 로그인 성공: idToken=$idToken")
                        onSuccess(idToken)
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e(TAG, "Google 토큰 파싱 실패", e)
                        onFailure(e)
                    }
                } else {
                    Log.e(TAG, "CustomCredential 타입이 예상과 다름: ${credential.type}")
                    onFailure(null)
                }
            }

            else -> {
                Log.e(TAG, "Credential 타입이 예상과 다름: ${credential.javaClass.name}")
                onFailure(null)
            }
        }
    }
}