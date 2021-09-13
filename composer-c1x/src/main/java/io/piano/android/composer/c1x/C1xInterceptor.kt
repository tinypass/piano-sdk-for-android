package io.piano.android.composer.c1x

import com.cxense.cxensesdk.CxenseSdk
import com.cxense.cxensesdk.model.CustomParameter
import com.cxense.cxensesdk.model.ExternalUserId
import com.cxense.cxensesdk.model.PageViewEvent
import io.piano.android.composer.ExperienceInterceptor
import io.piano.android.composer.model.ExperienceRequest
import io.piano.android.composer.model.ExperienceResponse
import io.piano.android.composer.model.events.ExperienceExecute
import timber.log.Timber

class C1xInterceptor(
    private val siteId: String
) : ExperienceInterceptor {

    override fun beforeExecute(
        request: ExperienceRequest
    ) {
        check(!request.url.isNullOrEmpty()) {
            "URL is required for C1X"
        }
    }

    override fun afterExecute(request: ExperienceRequest, response: ExperienceResponse) {
        response.apply {
            if (cxenseCustomerPrefix == null) {
                Timber.w("C1X hasn't configured")
                return
            }
            result.events.firstOrNull {
                it.eventData is ExperienceExecute
            }?.eventExecutionContext?.also { experienceExecutionContext ->
                val userState = when {
                    response.userId in listOf(null, "", "anon") -> ANON_STATE
                    experienceExecutionContext.accessList.isNullOrEmpty() -> REGISTERED_STATE
                    else -> ACTIVE_STATE
                }
                val externalUserId = response.userId?.let {
                    ExternalUserId(cxenseCustomerPrefix!!, it)
                }
                val event = PageViewEvent.Builder(
                    siteId,
                    request.url,
                    referrer = request.referer,
                    customParameters = mutableListOf(CustomParameter(PARAM_USERSTATE, userState)),
                ).apply {
                    if (externalUserId != null)
                        addExternalUserIds(externalUserId)
                }.build()
                CxenseSdk.getInstance().pushEvents(event)
            } ?: Timber.w("C1X can't find ExperienceExecute event")
        }
    }

    companion object {
        private const val ACTIVE_STATE = "hasActiveAccess"
        private const val ANON_STATE = "anon"
        private const val REGISTERED_STATE = "registered"
        private const val PARAM_USERSTATE = "userState"
    }
}
