package info.imdang.imdang.core.datastore

import androidx.datastore.core.DataStore
import info.imdang.imdang.core.data.datasource.local.SeoulAreaDataSource
import info.imdang.imdang.core.data.datasource.model.SeoulDistrictEntity
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SeoulAreaDataSourceImpl @Inject constructor(
    private val seoulDistrictPreferences : DataStore<SeoulDistrictPreferences>
) : SeoulAreaDataSource {
    override val seoulDistricts = seoulDistrictPreferences.data
        .map { pref ->
            pref.districtsList.map {
                SeoulDistrictEntity(
                    it.district,
                    it.dongList
                )
            }
        }
    override suspend fun setSeoulArea(seoulDistricts : List<SeoulDistrictEntity>) {
        seoulDistrictPreferences.updateData { pref->
            pref.copy {
                districts.clear()
                seoulDistricts.forEach { entity->
                    districts.add(
                        SeoulDistrict.newBuilder()
                            .setDistrict(entity.district)
                            .addAllDong(entity.dong)
                            .build()
                    )
                }
            }
        }
    }
}