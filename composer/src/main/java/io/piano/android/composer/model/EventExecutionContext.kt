package io.piano.android.composer.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
public class EventExecutionContext(
    @JvmField public val experienceId: String,
    @JvmField public val executionId: String,
    @JvmField public val trackingId: String,
    @JvmField public val splitTests: List<SplitTest>?,
    @JvmField public val currentMeterName: String?,
    @JvmField public val user: User?,
    @JvmField public val region: String?,
    @JvmField public val countryCode: String,
    @JvmField public val userSegments: UserSegmentsContainer,
    @JvmField public val accessList: List<Access>?,
    @JvmField public val activeMeters: List<ActiveMeter>?,
)
