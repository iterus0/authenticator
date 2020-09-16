package xyz.iterus.authenticator.feature.token.domain.model.hotp

import xyz.iterus.authenticator.feature.token.domain.toByteArray
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.math.abs

internal class RFC4226: HOTP {

    override fun generateOTP(secret: String, counter: Long, digits: Int, algorithm: HOTP.HashAlgorithm): String =
        formatOTP(generateOTPNumber(secret, counter, digits, algorithm), digits)

    override fun generateOTPNumber(secret: String, counter: Long, digits: Int, algorithm: HOTP.HashAlgorithm): Int {
        val hmac = generateHmac(algorithm, secret.toByteArray(), counter.toByteArray())

        val offset = hmac.last().toInt() and 0xF

        return hmac.slice(offset..offset+3)
            .map { it.toInt() }
            .mapIndexed { idx, it -> if (idx == 0) (it and 0x7F) else (it and 0xFF) }
            .asReversed().mapIndexed { idx, it -> it shl 8*idx }
            .reduce { acc, it -> acc or it }
    }

    private fun generateHmac(algorithm: HOTP.HashAlgorithm, secret: ByteArray, data: ByteArray): ByteArray {
        val algName = "Hmac$algorithm"

        with (Mac.getInstance(algName)) {
            init(SecretKeySpec(secret, algName))
            return doFinal(data)
        }
    }

    private fun formatOTP(otp: Int, digits: Int): String {
        val otpStr = otp.toString()
        val sizeDiff = digits - otpStr.length

        return if (sizeDiff >= 0) {
            ("0".repeat(sizeDiff) + otpStr)
        } else {
            otpStr.drop(abs(sizeDiff))
        }
    }


    // Overridden because of HOTPToken data class
    override fun equals(other: Any?): Boolean {
        return (javaClass == other?.javaClass)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
