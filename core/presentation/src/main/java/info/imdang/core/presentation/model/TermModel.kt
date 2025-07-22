package info.imdang.core.presentation.model

import info.imdang.imdang.core.domain.model.TermData

data class TermModel(
    val termsId: Int,
    val title: String,
    val url: String,
    val isEssential: Boolean,
)

fun TermData.toPresentation(): TermModel = TermModel(
    termsId = termsId,
    title = title,
    url = url,
    isEssential = isEssential
)