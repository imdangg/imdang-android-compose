package info.imdang.core.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.imdang.core.presentation.model.JoinRequestModel
import info.imdang.core.presentation.model.TermModel
import info.imdang.core.presentation.model.toDomain
import info.imdang.core.presentation.model.toPresentation
import info.imdang.imdang.core.domain.usecase.GetTermUseCase
import info.imdang.imdang.core.domain.usecase.PostJoinUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasicProfileInputViewModel @Inject constructor(
    private val getTermUseCase: GetTermUseCase,
    private val postJoinUseCase: PostJoinUseCase,
) : ViewModel() {
    private val _terms = MutableStateFlow<List<TermModel>>(emptyList())
    val terms: StateFlow<List<TermModel>> = _terms.asStateFlow()

    fun getTerms() {
        viewModelScope.launch {
            getTermUseCase()
                .catch {
                    TODO()
                }
                .collect { termList ->
                    _terms.value = termList.map { it.toPresentation() }
                }
        }
    }

    fun postJoin(
        nickname: String,
        birthDate: String,
        gender: String,
        deviceToken: String,
        onSuccess: () -> Unit,
    ) {
        val request = JoinRequestModel(nickname, birthDate, gender, deviceToken)
        viewModelScope.launch {
            postJoinUseCase(request.toDomain())
                .catch {
                    TODO()
                }
                .collect {
                    onSuccess()
                }
        }
    }
}

sealed interface TermsUiState {
    object Loading : TermsUiState
    data class Success(val terms: List<Term>) : TermsUiState
    data class Error(val message: String) : TermsUiState
}

enum class Gender {
    MALE,
    FEMALE
}