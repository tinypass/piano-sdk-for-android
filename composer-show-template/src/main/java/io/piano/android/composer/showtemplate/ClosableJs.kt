package io.piano.android.composer.showtemplate

import androidx.annotation.UiThread

interface ClosableJs {
    @UiThread
    fun closeOverridden(eventData: String? = null)
}
