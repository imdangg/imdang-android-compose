package info.imdang.imdang.core.network.model

import info.imdang.imdang.core.data.datasource.model.TermEntity
import info.imdang.imdang.core.network.RemoteMapper

data class TermResponse(
    val termsId: Int,
    val title: String,
    val url: String,
    val isEssential: Boolean,
) : RemoteMapper<TermEntity> {
    override fun toData(): TermEntity =
        TermEntity(
            termsId = termsId,
            title = title,
            url = url,
            isEssential = isEssential
        )
}
