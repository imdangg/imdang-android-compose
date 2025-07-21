package info.imdang.core.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

enum class Gender {
    MALE,
    FEMALE
}

@HiltViewModel
class BasicProfileInputViewModel @Inject constructor(

) : ViewModel() {

}