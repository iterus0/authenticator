package xyz.iterus.authenticator.feature.token.domain.model.hotp

import xyz.iterus.authenticator.library.base.toByteArray
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.math.abs

internal class RFC4226: HOTP {

    override fun generate(secret: String, counter: Long, digits: Int, algorithm: HOTP.HashAlgorithm): String =
        format(generateNumber(secret, counter, digits, algorithm), digits)

    override fun generateNumber(secret: String, counter: Long, digits: Int, algorithm: HOTP.HashAlgorithm): Int {
        val hmac = generateHmac(algorithm, secret.toByteArray(), counter.toByteArray())
        val offset = hmac.last().toInt() and 0xF

        return hmac.slice(offset..offset+3)
            .map { it.toInt() }
            .mapIndexed { idx, byte -> if (idx == 0) (byte and 0x7F) else (byte and 0xFF) }
            .asReversed().mapIndexed { idx, byte -> byte shl 8*idx }
            .reduce { acc, byte -> acc or byte }
    }

    private fun generateHmac(algorithm: HOTP.HashAlgorithm, secret: ByteArray, data: ByteArray): ByteArray {
        val algName = "Hmac$algorithm"

        with (Mac.getInstance(algName)) {
            init(SecretKeySpec(secret, algName))
            return doFinal(data)
        }
    }

    private fun format(otp: Int, digits: Int): String {
        val otpStr = otp.toString()
        val sizeDiff = digits - otpStr.length

        return if (sizeDiff > 0) {
            ("0".repeat(sizeDiff) + otpStr)
        } else {
            otpStr.drop(abs(sizeDiff))
        }
    }


    // Overridden because of HOTPToken data class
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return (javaClass == other?.javaClass)
    }

    override fun hashCode(): Int = javaClass.hashCode()
}
