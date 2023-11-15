package io.piano.android.id

object FormHelper {
    @JvmStatic
    fun buildUrl(formName: String?, hideCompletedFields: Boolean, trackingId: String) =
        PianoId.getInstance().getFormUrl(formName, hideCompletedFields, trackingId)

    @JvmStatic
    fun buildConsentsCode() =
        @Suppress("ktlint:standard:max-line-length")
        """PianoIDMobileSDK.messageCallback('{"event":"consentWithModes","params":"${PianoId.getInstance().consentsDataProvider.consents}"}')"""
}
