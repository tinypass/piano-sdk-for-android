package io.piano.android.id

object FormUrlBuilder {
    @JvmStatic
    fun buildUrl(formName: String?, hideCompletedFields: Boolean, trackingId: String) =
        PianoId.getClient().getFormUrl(formName, hideCompletedFields, trackingId)
}
