package io.piano.android.id

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import io.piano.android.id.models.OAuthResult

internal class OAuthResultContract : ActivityResultContract<String, OAuthResult?>() {
    private val client: PianoIdClient = PianoId.getClient()

    override fun createIntent(context: Context, input: String): Intent =
        client.buildSocialAuthIntent(context, input)

    override fun parseResult(resultCode: Int, intent: Intent?): OAuthResult? =
        runCatching {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    checkNotNull(intent) { "Result intent is null" }
                    val provider = requireNotNull(intent.getStringExtra(PianoId.KEY_OAUTH_PROVIDER_NAME)) {
                        "provider must be filled"
                    }
                    val token = requireNotNull(intent.getStringExtra(PianoId.KEY_OAUTH_PROVIDER_TOKEN)) {
                        "token must be filled"
                    }
                    OAuthResult.success(client.buildResultJsCommand(provider, token))
                }
                Activity.RESULT_CANCELED -> null
                else -> {
                    throw intent?.getIntExtra(PianoId.KEY_ERROR, 0)
                        ?.let { client.getStoredException(it) }
                        ?: IllegalStateException("Unknown error")
                }
            }
        }.recover {
            OAuthResult.failure(it)
        }.getOrNull()
}
