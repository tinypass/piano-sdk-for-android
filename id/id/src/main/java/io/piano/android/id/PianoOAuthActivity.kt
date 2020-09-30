package io.piano.android.id

import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.RestrictTo
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import io.piano.android.id.PianoIdClient.Companion.toPianoIdException

abstract class PianoOAuthActivity : AppCompatActivity() {
    @VisibleForTesting
    @RestrictTo(RestrictTo.Scope.LIBRARY)
    internal lateinit var client: PianoIdClient

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        client = PianoId.getClient()
    }

    /**
     * Sets success data for Piano ID OAuth
     *
     * @param providerName Provider name
     * @param token        Provider token
     */
    protected open fun setSuccessResult(providerName: String, token: String?) =
        setResult(
            RESULT_OK,
            Intent()
                .putExtra(PianoId.KEY_OAUTH_PROVIDER_NAME, providerName)
                .putExtra(PianoId.KEY_OAUTH_PROVIDER_TOKEN, token)
        )

    /**
     * Sets failure data for Piano ID OAuth
     *
     * @param throwable Throwable, that will be processed
     */
    protected open fun setFailureResult(throwable: Throwable) =
        setResult(
            PianoId.RESULT_ERROR,
            Intent().putExtra(PianoId.KEY_ERROR, client.saveException(throwable.toPianoIdException()))
        )
}
