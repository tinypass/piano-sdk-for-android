package io.piano.android.composer.model

/**
 * Class for passing cookies from Edge CDN
 * @property tbc The Browser Cookie
 * @property xbc The Experience Cookie
 * @property pcer The Experience Result Cookie
 */
public class EdgeResult @JvmOverloads constructor(
    public val tbc: String = "",
    public val xbc: String = "",
    public val pcer: String = "",
)
