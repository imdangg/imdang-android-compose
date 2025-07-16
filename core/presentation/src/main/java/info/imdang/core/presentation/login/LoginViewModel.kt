package info.imdang.core.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.imdang.core.presentation.model.toPresentation
import info.imdang.imdang.core.common.dataresource.mapDataResource
import info.imdang.imdang.core.domain.usecase.GetKakaoLoginUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getKakaoLoginUseCase: GetKakaoLoginUseCase
) : ViewModel() {

    fun getLogin(provider: String, token: String
    ) {
        viewModelScope.launch {
            getKakaoLoginUseCase(provider, token)
                .mapDataResource { it.toPresentation() }
        }
    }
}