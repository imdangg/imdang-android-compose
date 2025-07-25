package info.imdang.imdang.core.domain.repository

import info.imdang.imdang.core.domain.model.SeoulDistrict
import kotlinx.coroutines.flow.Flow


interface SeoulAreaRepository {
    fun loadSeoulAreaFormJson()

    fun getSavedSeoulAreaData() : Flow<List<SeoulDistrict>>
}