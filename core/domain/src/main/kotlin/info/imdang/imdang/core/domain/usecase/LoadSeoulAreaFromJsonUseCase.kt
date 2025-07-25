package info.imdang.imdang.core.domain.usecase

import info.imdang.imdang.core.domain.repository.SeoulAreaRepository
import javax.inject.Inject

class LoadSeoulAreaFromJsonUseCase @Inject constructor(
    private val seoulAreaRepository: SeoulAreaRepository
){
    operator fun invoke() = seoulAreaRepository.loadSeoulAreaFormJson()
}