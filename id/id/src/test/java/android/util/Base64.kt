package android.util

object Base64 {
    @JvmStatic
    fun decode(str: String, flags: Int): ByteArray = str.toByteArray()

    @JvmStatic
    fun encode(bytes: ByteArray, flags: Int): ByteArray = bytes
}
