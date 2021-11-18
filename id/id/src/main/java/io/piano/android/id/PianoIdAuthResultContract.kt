package io.piano.android.id

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import io.piano.android.id.models.PianoIdAuthResult

class PianoIdAuthResultContract : ActivityResultContract<PianoIdClient.SignInContext, PianoIdAuthResult?>() {
    private val client: PianoIdClient by lazy { PianoId.getClient() }

    override fun createIntent(context: Context, input: PianoIdClient.SignInContext): Intent =
        with(input) {
            PianoIdActivity.buildIntent(context, disableSignUp, widget)
        }

    override fun parseResult(resultCode: Int, intent: Intent?): PianoIdAuthResult? =
        runCatching {
            when (resultCode) {
                Activity.RESULT_CANCELED -> null
                else -> {
                    checkNotNull(intent) { "Result intent is null" }
                    with(intent) {
                        val code = getIntExtra(PianoId.KEY_ERROR, 0)
                        if (code == 0) {
                            PianoIdAuthResult.success(
                                getParcelableExtra(PianoId.KEY_TOKEN),
                                getBooleanExtra(PianoId.KEY_IS_NEW_USER, false)
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
