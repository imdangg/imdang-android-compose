package info.imdang.core.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.imdang.core.presentation.model.JoinRequestModel
import info.imdang.core.presentation.model.TermModel
import info.imdang.core.presentation.model.toDomain
import info.imdang.core.presentation.model.toPresentation
import info.imdang.imdang.core.domain.usecase.GetTermUseCase
import info.imdang.imdang.core.domain.usecase.PostTermsAgreeUseCase
import info.imdang.imdang.core.domain.usecase.PutJoinUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasicProfileInputViewModel @Inject constructor(
    private val getTermUseCase: GetTermUseCase,
    private val postTermsAgreeUseCase: PostTermsAgreeUseCase,
    private val putJoinUseCase: PutJoinUseCase,
) : ViewModel() {
    private val tag = BasicProfileInputViewModel::class.simpleName

    val terms: StateFlow<List<TermModel>> = getTermUseCase()
        .catch { e ->
            Log.e(tag, "getTerms 실패: ${e.message}", e)
        }
        .map { termList -> termList.map { it.toPresentation() } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun postTermsAgree(termsIds: List<Int>) {
        viewModelScope.launch {
            postTermsAgreeUseCase(termsIds)
                .catch { e ->
                    Log.e(tag, "postTermsAgree 실패: ${e.message}", e)
                }
                .collect {
                    Log.d(tag, "postTermsAgree 성공")
                }
        }
    }

    fun putJoin(
        nickname: String,
        birthDate: String,
        gender: String,
        deviceToken: String,
        onSuccess: () -> Unit,
    ) {
        val request = JoinRequestModel(nickname, birthDate, gender, deviceToken)
        viewModelScope.launch {
            putJoinUseCase(request.toDomain())
                .catch { e ->
                    Log.e(tag, "postJoin 실패: ${e.message}", e)
                }
                .collect {
                    onSuccess()
                }
        }
    }
}

enum class Gender {
    MALE,
    FEMALE
}