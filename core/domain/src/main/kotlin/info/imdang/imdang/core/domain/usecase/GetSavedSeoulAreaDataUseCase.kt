package info.imdang.imdang.core.domain.usecase

import info.imdang.imdang.core.domain.repository.SeoulAreaRepository
import javax.inject.Inject

class GetSavedSeoulAreaDataUseCase @Inject constructor(
    private val seoulAreaRepository: SeoulAreaRepository
) {
    operator fun invoke() = seoulAreaRepository.getSavedSeoulAreaData()
}