package info.imdang.core.presentation.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.imdang.core.presentation.Result
import info.imdang.core.presentation.asResult
import info.imdang.core.presentation.model.ReissueRequestModel
import info.imdang.core.presentation.model.toDomain
import info.imdang.imdang.core.domain.usecase.GetSavedLoginDataUseCase
import info.imdang.imdang.core.domain.usecase.PostReissueUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getSavedLoginDataUseCase: GetSavedLoginDataUseCase,
    private val postReissueUseCase: PostReissueUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val loginData = getSavedLoginDataUseCase().firstOrNull()

            if (loginData == null) {
                _uiState.value = SplashUiState.NavigateToLogin
                return@launch
            }

            val reissueRequestModel = ReissueRequestModel(
                memberId = loginData.memberId,
                refreshToken = loginData.refreshToken
            )

            postReissueUseCase(reissueRequestModel.toDomain())
                .asResult()
                .collect { loginResult ->
                    when (loginResult) {
                        Result.Loading -> SplashUiState.Loading
                        is Result.Error -> SplashUiState.NavigateToLogin
                        is Result.Success -> SplashUiState.NavigateToHome
                    }
                }
        }
    }
}

sealed class SplashUiState {
    data object Loading : SplashUiState()
    data object NavigateToLogin : SplashUiState()
    data object NavigateToHome : SplashUiState()
}