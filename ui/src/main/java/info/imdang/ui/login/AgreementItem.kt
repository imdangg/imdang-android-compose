package info.imdang.ui.login

import androidx.annotation.StringRes
import info.imdang.ui.R

sealed class AgreementItem(
    val required: Boolean,
    @StringRes val labelRes: Int,
    val url: String
) {
    data object TermsOfUse : AgreementItem(
        required = true,
        labelRes = R.string.agreement_terms,
        url = ""
    )

    data object PrivacyPolicy : AgreementItem(
        required = true,
        labelRes = R.string.agreement_privacy,
        url = ""
    )

    data object MarketingConsent : AgreementItem(
        required = false,
        labelRes = R.string.agreement_marketing,
        url = ""
    )

    companion object {
        val all = listOf(TermsOfUse, PrivacyPolicy, MarketingConsent)
    }
}