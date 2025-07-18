package info.imdang.core.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.imdang.core.presentation.model.LoginModel
import info.imdang.core.presentation.model.LoginRequestModel
import info.imdang.core.presentation.model.toDomain
import info.imdang.core.presentation.model.toPresentation
import info.imdang.imdang.core.domain.usecase.GetLoginUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase
) : ViewModel() {

    fun getLogin(
        provider: String,
        token: String,
        onSuccess: (LoginModel) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val request = LoginRequestModel(provider, token)
        viewModelScope.launch {
            getLoginUseCase(request.toDomain())
                .catch {
                    onError(it)
                }
                .collect { loginData ->
                    onSuccess(loginData.toPresentation())
                }
        }
    }
}