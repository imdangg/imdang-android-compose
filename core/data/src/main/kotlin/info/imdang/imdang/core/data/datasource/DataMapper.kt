package info.imdang.imdang.core.data.datasource

internal interface DataMapper<DomainModel> {
    fun toDomain(): DomainModel
}