package io.piano.android.composer.model

/**
 * Context of the experience execution, which is sent along with an exposure of its event.
 *
 * @property request The experience request, which returned the event.
 * @property browserId The browser ID at the moment of the experience response.
 * @property userToken The user token at the moment of the experience response.
 */
internal class ExposureContext(
    val request: ExperienceRequest,
    val browserId: String?,
    val userToken: String?,
)
