package info.imdang.imdang.core.network.interceptor

import android.util.Log
import com.skydoves.sandwich.ApiResponse
import info.imdang.imdang.core.data.datasource.local.AuthLocalDataSource
import info.imdang.imdang.core.data.datasource.model.LoginEntity
import info.imdang.imdang.core.data.datasource.model.ReissueRequestEntity
import info.imdang.imdang.core.data.datasource.remote.AuthRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

internal class TokenRefreshInterceptor @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
) : Authenticator {

    private val tag = TokenRefreshInterceptor::class.simpleName

    private val refreshMutex = Mutex()

    override fun authenticate(route: Route?, response: Response): Request? {
        if (responseCount(response) >= 2) {
            Log.w(tag, "401 응답이 반복됨 (2회 이상) → 더 이상 인증 시도하지 않고 로그인 화면으로 이동")
            handleTokenRefreshFailure()
            return null
        }

        // 401 에러가 발생한 경우 토큰 리프레시 시도
        if (response.code == 401) {
            Log.d(tag, "401 에러 발생, 토큰 리프레시 시도")

            return runBlocking {
                refreshMutex.withLock {
                    try {
                        // 저장된 로그인 정보 가져오기
                        val savedLoginEntity = authLocalDataSource.loginEntity.first()
                        if (savedLoginEntity == null) {
                            Log.w(tag, "저장된 로그인 정보가 없음, 로그인 화면으로 이동")
                            handleTokenRefreshFailure()
                            return@withLock null
                        }

                        // 토큰 리프레시 요청
                        val reissueRequest = ReissueRequestEntity(
                            memberId = savedLoginEntity.memberId,
                            refreshToken = savedLoginEntity.refreshToken
                        )

                        val reissueResponse = authRemoteDataSource.postReissue(reissueRequest)

                        // 토큰 리프레시 성공 시
                        if (reissueResponse is ApiResponse.Success) {
                            val reissueData = reissueResponse.data
                            Log.d(tag, "토큰 리프레시 성공, 새로운 토큰으로 로컬 저장소 업데이트")

                            // 새로운 토큰으로 로컬 저장소 업데이트
                            val newLoginEntity = LoginEntity(
                                memberId = savedLoginEntity.memberId,
                                isJoined = savedLoginEntity.isJoined,
                                accessToken = reissueData.accessToken,
                                refreshToken = reissueData.refreshToken
                            )
                            authLocalDataSource.setLoginEntity(newLoginEntity)

                            Log.d(tag, "새로운 토큰으로 API 재요청")

                            // 새로운 토큰으로 원본 요청 재시도
                            return@withLock response.request.newBuilder()
                                .removeHeader("Authorization")
                                .addHeader("Authorization", "Bearer ${reissueData.accessToken}")
                                .build()
                        } else {
                            Log.e(tag, "토큰 리프레시 실패")
                            handleTokenRefreshFailure()
                            return@withLock null
                        }

                    } catch (e: Exception) {
                        Log.e(tag, "토큰 리프레시 중 예외 발생", e)
                        handleTokenRefreshFailure()
                        return@withLock null
                    }
                }
            }
        }
        return null
    }

    private fun responseCount(response: Response): Int {
        var count = 1
        var prior = response.priorResponse
        while (prior != null) {
            count++
            prior = prior.priorResponse
        }
        return count
    }

    private fun handleTokenRefreshFailure() = runBlocking {
        authLocalDataSource.clearLoginEntity()
        // 로그인 화면으로 이동하는 로직을 여기에 추가 (특정 eventFlow를 emit하고 ViewModel을 통해 UI에 알림)
    }
}