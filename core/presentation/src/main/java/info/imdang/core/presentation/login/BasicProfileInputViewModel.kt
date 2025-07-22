package info.imdang.core.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import info.imdang.core.presentation.model.JoinRequestModel
import info.imdang.core.presentation.model.toDomain
import info.imdang.imdang.core.domain.usecase.PostJoinUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasicProfileInputViewModel @Inject constructor(
    private val postJoinUseCase: PostJoinUseCase,
) : ViewModel() {

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

enum class Gender {
    MALE,
    FEMALE
}