package info.imdang.imdang.core.data.datasource.local

import android.content.Context
import info.imdang.imdang.core.data.datasource.model.SeoulDistrictEntity
import kotlinx.coroutines.flow.Flow


interface SeoulAreaDataSource {
    val seoulDistricts : Flow<List<SeoulDistrictEntity?>>

    suspend fun setSeoulArea(seoulDistricts : List<SeoulDistrictEntity>)
}