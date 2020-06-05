package io.piano.android.id.models

import androidx.test.core.os.Parcelables
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class PianoIdTokenTest {

    @Test
    fun parcelableCheck() {
        val date = Date()
        val token = PianoIdToken(DUMMY, date, DUMMY2)
        with(Parcelables.forceParcel(token, PianoIdToken.CREATOR)) {
            assertEquals(DUMMY, accessToken)
            assertEquals(date, expiresIn)
            assertEquals(DUMMY2, refreshToken)
        }
    }

    companion object {
        const val DUMMY = "dummy"
        const val DUMMY2 = "dummy2"
    }
}
