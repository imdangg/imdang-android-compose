package info.imdang.imdang.core.data.datasource.impl

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import info.imdang.core.data.R
import info.imdang.imdang.core.data.datasource.local.SeoulAreaDataSource
import info.imdang.imdang.core.data.datasource.model.SeoulDistrictEntity
import info.imdang.imdang.core.domain.model.SeoulDistrict
import info.imdang.imdang.core.domain.repository.SeoulAreaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SeoulAreaRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val seoulAreaDataSource: SeoulAreaDataSource
) : SeoulAreaRepository{

    override fun loadSeoulAreaFormJson() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val inputStream = context.resources.openRawResource(R.raw.seoul_dong)
                val jsonStr = inputStream.bufferedReader().use { it.readText() }
                Log.d("loadFromJson", "JSON string length: ${jsonStr.length}")
                val gson = Gson()
                val type = object : TypeToken<List<SeoulDistrict>>() {}.type
                val districts: List<SeoulDistrict> = gson.fromJson(jsonStr, type)
                Log.d("loadFromJson", "Parsed districts count: ${districts.size}")
                val entities = districts.map {
                    SeoulDistrictEntity(it.district, it.dong)
                }
                seoulAreaDataSource.setSeoulArea(entities)

            } catch (e: Exception) {
                Log.e("loadFromJson", "Error loading json", e)
                throw e
            }
        }
    }

    override fun getSavedSeoulAreaData(): Flow<List<SeoulDistrict>> =
        seoulAreaDataSource.seoulDistricts.map { list ->
            list.filterNotNull().map { it.toDomain() }
        }
}