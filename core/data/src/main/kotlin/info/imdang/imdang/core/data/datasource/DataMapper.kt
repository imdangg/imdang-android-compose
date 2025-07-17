package info.imdang.imdang.core.data.datasource

internal interface DataMapper<DomainModel> {
    fun toDomain(): DomainModel
}

@Suppress("UNCHECKED_CAST")
internal fun <EntityModel, DomainModel> EntityModel.toDomainModel(): DomainModel {
    return when (this) {
        is DataMapper<*> -> (this as DataMapper<DomainModel>).toDomain()
        is List<*> -> {
            (this as List<*>).map {
                it?.toDomainModel<Any, Any>() as DomainModel
            } as DomainModel
        }

        is Unit, is Boolean, is Int, is String, is Byte, is Short, is Long, is Char -> this as DomainModel
        else -> throw IllegalArgumentException("DataModel은 DataMapper<>, List<DataMapper<>>, Unit 중 하나여야 함")
    }
}

internal fun <EntityModel : DataMapper<DomainModel>, DomainModel> List<EntityModel>.toDomain(): List<DomainModel> {
    return map(DataMapper<DomainModel>::toDomain)
}