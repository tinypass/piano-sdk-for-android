package io.piano.android.id.models

import com.squareup.moshi.JsonClass
import io.piano.android.consents.models.ConsentMode
import io.piano.android.consents.models.Product
import io.piano.android.consents.models.Purpose

@JsonClass(generateAdapter = true)
class ConsentData(
    val purpose: Purpose,
    val mode: ConsentMode,
    val products: List<Product>,
)
