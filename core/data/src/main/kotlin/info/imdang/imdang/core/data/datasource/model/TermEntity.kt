package info.imdang.imdang.core.data.datasource.model

import info.imdang.imdang.core.data.datasource.DataMapper
import info.imdang.imdang.core.domain.model.TermData

data class TermEntity(
    val termsId: Int,
    val title: String,
    val url: String,
    val isEssential: Boolean,
) : DataMapper<TermData> {
    override fun toDomain(): TermData =
        TermData(
            termsId = termsId,
            title = title,
            url = url,
            isEssential = isEssential
        )
}

