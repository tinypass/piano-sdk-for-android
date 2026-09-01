package io.piano.android.id

public object FormHelper {
    @JvmStatic
    public fun buildUrl(formName: String?, hideCompletedFields: Boolean, trackingId: String): String =
        PianoId.getInstance().getFormUrl(formName, hideCompletedFields, trackingId)
}
