package io.piano.sample

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.piano.android.id.models.PianoIdToken
import timber.log.Timber

class PrefsStorage(context: Context, moshi: Moshi) {
    private val sharedPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    private val pianoIdTokenJsonAdapter: JsonAdapter<PianoIdToken> = moshi.adapter(PianoIdToken::class.java).nullSafe()

    var pianoIdToken: PianoIdToken?
        get() = runCatching {
            sharedPreferences.getString(KEY_TOKEN, null)?.let { pianoIdTokenJsonAdapter.fromJson(it) }
        }.onFailure {
            Timber.e(it)
        }.getOrNull()
        set(token) = sharedPreferences.edit { putString(KEY_TOKEN, pianoIdTokenJsonAdapter.toJson(token)) }

    companion object {
        const val KEY_TOKEN = "piano_token"
    }
}
