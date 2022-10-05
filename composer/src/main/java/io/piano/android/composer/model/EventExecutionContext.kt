package io.piano.android.composer.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class EventExecutionContext(
    @JvmField val experienceId: String,
    @JvmField val executionId: String,
    @JvmField val trackingId: String,
    @JvmField val splitTests: List<SplitTest>?,
    @JvmField val currentMeterName: String?,
    @JvmField val user: User?,
    @JvmField val region: String?,
    @JvmField val countryCode: String,
    @JvmField val userSegments: UserSegmentsContainer,
    @JvmField val accessList: List<Access>?,
    @JvmField val activeMeters: List<ActiveMeter>?
)
