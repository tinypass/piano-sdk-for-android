package io.piano.android.id

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import io.piano.android.id.models.PianoIdAuthResult

public class PianoIdAuthResultContract : ActivityResultContract<PianoIdClient.SignInContext, PianoIdAuthResult?>() {
    private val client: PianoIdClient by lazy { PianoId.getInstance() }

    override fun createIntent(context: Context, input: PianoIdClient.SignInContext): Intent = with(input) {
        PianoIdActivity.buildIntent(context, disableSignUp, widget, stage)
    }

    override fun parseResult(resultCode: Int, intent: Intent?): PianoIdAuthResult? = runCatching {
        when (resultCode) {
            Activity.RESULT_CANCELED -> null
            else -> {
                checkNotNull(intent) { "Result intent is null" }
                with(intent) {
                    val code = getIntExtra(PianoId.KEY_ERROR, 0)
                    if (code == 0) {
                        @Suppress("DEPRECATION") // deprecated since Android 13
                        PianoIdAuthResult.success(
                            getParcelableExtra(PianoId.KEY_TOKEN),
                            getBooleanExtra(PianoId.KEY_IS_NEW_USER, false),
                        )
                    } else {
                        PianoIdAuthResult.failure(requireNotNull(client.getStoredException(code)))
                    }
                }
            }
        }
    }.recover {
        PianoIdAuthResult.failure(it)
    }.getOrNull()
}
