package info.imdang.imdang.core.network

interface RemoteMapper<DataModel> {
    fun toData(): DataModel
}