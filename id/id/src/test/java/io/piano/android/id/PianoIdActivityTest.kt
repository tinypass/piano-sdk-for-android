package io.piano.android.id

import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockitokotlin2.*
import io.piano.android.id.models.PianoIdToken
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.Shadows
import java.util.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class PianoIdActivityTest {
    @get:Rule
    val rule = activityScenarioRule<PianoIdActivity>(
        PianoIdActivity.buildIntent(
            ApplicationProvider.getApplicationContext(),
            DISABLE_SIGNUP,
            WIDGET
        )
    )

    private val tokenCallback: PianoIdCallback<PianoIdToken> = mock()
    private val client: PianoIdClient = mock {
        on { tokenCallback } doReturn tokenCallback
        on { saveException(any()) } doReturn CODE
    }
    private val callbackCaptor = argumentCaptor<PianoIdCallback<String>>()

    init {
        PianoId.client = client
    }

    @Test
    fun createGetSignInUrlSuccess() {
        rule.scenario.onActivity {
            val shadow = Shadows.shadowOf(it.webView)
            verify(client).getSignInUrl(eq(DISABLE_SIGNUP), eq(WIDGET), callbackCaptor.capture())
            with(callbackCaptor.lastValue) {
                onSuccess(URL)
                assertEquals(URL, shadow.lastLoadedUrl)
            }
        }
    }

    @Test
    fun createGetSignInUrlFailed() {
        rule.scenario.onActivity {
            val shadow = Shadows.shadowOf(it)
            val exc = PianoIdException()
            verify(client).getSignInUrl(eq(DISABLE_SIGNUP), eq(WIDGET), callbackCaptor.capture())
            with(callbackCaptor.lastValue) {
                onFailure(exc)
                assertEquals(PianoId.RESULT_ERROR, shadow.resultCode)
                assertEquals(CODE, shadow.resultIntent.getIntExtra(PianoId.KEY_ERROR, 0))
                assertTrue { it.isFinishing }
                verify(client).saveException(exc)
                verify(tokenCallback).onFailure(exc)
            }

        }
    }

    @Test
    fun socialLoginResultOkWithoutData() {
        rule.scenario.onActivity {
            val oauthIntent = Intent()
            val data = Intent()
            whenever(client.buildSocialAuthIntent(any(), any())).doReturn(oauthIntent)
            val shadow = Shadows.shadowOf(it)
            it.socialLogin(DUMMY)
            shadow.receiveResult(
                oauthIntent,
                Activity.RESULT_OK,
                data
            )
            assertEquals(PianoId.RESULT_ERROR, shadow.resultCode)
            assertEquals(CODE, shadow.resultIntent.getIntExtra(PianoId.KEY_ERROR, 0))
            assertTrue { it.isFinishing }
            val excCaptor = argumentCaptor<PianoIdException>()
            verify(client).saveException(excCaptor.capture())
            with(excCaptor.lastValue) {
                assertTrue { cause is IllegalArgumentException }
                verify(tokenCallback).onFailure(this)
            }
        }
    }

    @Test
    fun socialLoginResultOkWithData() {
        rule.scenario.onActivity {
            val oauthIntent = Intent()
            val data = Intent()
                .putExtra(PianoId.KEY_OAUTH_PROVIDER_NAME, DUMMY)
                .putExtra(PianoId.KEY_OAUTH_PROVIDER_TOKEN, DUMMY2)
            whenever(client.buildSocialAuthIntent(any(), any())).doReturn(oauthIntent)
            whenever(client.buildResultJsCommand(any(), any())).doReturn(DUMMY)
            val shadow = Shadows.shadowOf(it)
            it.socialLogin(DUMMY)
            shadow.receiveResult(
                oauthIntent,
                Activity.RESULT_OK,
                data
            )
            assertFalse { it.isFinishing }
            verify(client).buildResultJsCommand(DUMMY, DUMMY2)
        }
    }

    @Test
    fun socialLoginResultCancelled() {
        rule.scenario.onActivity {
            val oauthIntent = Intent()
            val data = Intent()
            whenever(client.buildSocialAuthIntent(any(), any())).doReturn(oauthIntent)
            val shadow = Shadows.shadowOf(it)
            it.socialLogin(DUMMY)
            shadow.receiveResult(
                oauthIntent,
                Activity.RESULT_CANCELED,
                data
            )
            assertEquals(Activity.RESULT_CANCELED, shadow.resultCode)
            assertTrue { it.isFinishing }
        }
    }

    @Test
    fun socialLoginResultError() {
        rule.scenario.onActivity {
            val oauthIntent = Intent()
            val data = Intent().putExtra(PianoId.KEY_ERROR, CODE)
            whenever(client.buildSocialAuthIntent(any(), any())).doReturn(oauthIntent)
            val shadow = Shadows.shadowOf(it)
            it.socialLogin(DUMMY)
            shadow.receiveResult(
                oauthIntent,
                PianoId.RESULT_ERROR,
                data
            )
            assertEquals(PianoId.RESULT_ERROR, shadow.resultCode)
            assertEquals(CODE, shadow.resultIntent.getIntExtra(PianoId.KEY_ERROR, 0))
            assertTrue { it.isFinishing }
            val excCaptor = argumentCaptor<PianoIdException>()
            verify(client).saveException(excCaptor.capture())
            with(excCaptor.lastValue) {
                verify(tokenCallback).onFailure(this)
            }
        }
    }

    @Test
    fun socialLoginFailed() {
        rule.scenario.onActivity {
            val oauthIntent = Intent()
            whenever(client.buildSocialAuthIntent(any(), any())).doReturn(oauthIntent)
            val shadow = Shadows.shadowOf(it)
            it.socialLogin(DUMMY)
            shadow.receiveResult(
                oauthIntent,
                Activity.RESULT_CANCELED,
                null
            )
            assertEquals(PianoId.RESULT_ERROR, shadow.resultCode)
            assertEquals(CODE, shadow.resultIntent.getIntExtra(PianoId.KEY_ERROR, 0))
            assertTrue { it.isFinishing }
            val excCaptor = argumentCaptor<PianoIdException>()
            verify(client).saveException(excCaptor.capture())
            with(excCaptor.lastValue) {
                assertTrue { cause is IllegalStateException }
                verify(tokenCallback).onFailure(this)
            }
        }
    }

    @Test
    fun socialLoginStartException() {
        rule.scenario.onActivity {
            val exc = PianoIdException()
            whenever(client.buildSocialAuthIntent(any(), any())).doThrow(exc)
            val shadow = Shadows.shadowOf(it)
            it.socialLogin(DUMMY)
            assertEquals(PianoId.RESULT_ERROR, shadow.resultCode)
            assertEquals(CODE, shadow.resultIntent.getIntExtra(PianoId.KEY_ERROR, 0))
            assertTrue { it.isFinishing }
            verify(client).saveException(exc)
            verify(tokenCallback).onFailure(exc)
        }
    }

    @Test
    fun loginSuccess() {
        rule.scenario.onActivity {
            val date = Date()
            val token = PianoIdToken(DUMMY, date, DUMMY2)
            whenever(client.buildToken(any())).doReturn(token)
            val shadow = Shadows.shadowOf(it)
            it.loginSuccess(DUMMY)
            assertEquals(Activity.RESULT_OK, shadow.resultCode)
            assertEquals(token, shadow.resultIntent.getParcelableExtra(PianoId.KEY_TOKEN))
            assertTrue { it.isFinishing }
            verify(tokenCallback).onSuccess(token)
        }
    }

    @Test
    fun loginSuccessStartException() {
        rule.scenario.onActivity {
            val exc = PianoIdException()
            whenever(client.buildToken(any())).doThrow(exc)
            val shadow = Shadows.shadowOf(it)
            it.loginSuccess(DUMMY)
            assertEquals(PianoId.RESULT_ERROR, shadow.resultCode)
            assertEquals(CODE, shadow.resultIntent.getIntExtra(PianoId.KEY_ERROR, 0))
            assertTrue { it.isFinishing }
            verify(client).saveException(exc)
            verify(tokenCallback).onFailure(exc)
        }
    }

    @Test
    fun cancel() {
        rule.scenario.onActivity {
            val shadow = Shadows.shadowOf(it)
            it.cancel()
            assertEquals(Activity.RESULT_CANCELED, shadow.resultCode)
            assertTrue { it.isFinishing }
        }
    }

    companion object {
        const val DUMMY = "dummy"
        const val DUMMY2 = "dummy2"
        const val DISABLE_SIGNUP = true
        const val WIDGET = "widget"
        const val URL = "http://localhost/"
        const val CODE = 1234
    }
}
