package io.piano.android.composer.model

/**
 * Class for passing cookies from Edge CDN
 * @property tbc The Browser Cookie
 * @property xbc The Experience Cookie
 * @property pcer The Experience Result Cookie
 */
class EdgeResult @JvmOverloads constructor(
    val tbc: String = "",
    val xbc: String = "",
    val pcer: String = "",
)
