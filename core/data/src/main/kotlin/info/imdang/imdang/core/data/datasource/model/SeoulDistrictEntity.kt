package info.imdang.imdang.core.data.datasource.model

import info.imdang.imdang.core.data.datasource.DataMapper
import info.imdang.imdang.core.domain.model.SeoulDistrict

data class SeoulDistrictEntity(
    val district: String,
    val dong: List<String>
) : DataMapper<SeoulDistrict>{
    override fun toDomain(): SeoulDistrict =
        SeoulDistrict(
            district = district,
            dong = dong
        )
}
