package io.piano.android.id

public object FormHelper {
    @JvmStatic
    public fun buildUrl(formName: String?, hideCompletedFields: Boolean, trackingId: String): String =
        PianoId.getInstance().getFormUrl(formName, hideCompletedFields, trackingId)

    @JvmStatic
    public fun buildConsentsCode(): String =
        @Suppress("ktlint:standard:max-line-length")
        """PianoIDMobileSDK.messageCallback('{"event":"consentWithModes","params":"${PianoId.getInstance().consentsDataProvider.consents}"}')"""
}
