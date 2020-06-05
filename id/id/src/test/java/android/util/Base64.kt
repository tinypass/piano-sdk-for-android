package android.util

object Base64 {
    @JvmStatic
    fun decode(str: String, flags: Int): ByteArray = str.toByteArray()
}
