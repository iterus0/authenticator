package xyz.iterus.authenticator.token.hotp

import xyz.iterus.authenticator.common.utils.toByteArray
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class RFC4226 : HOTP {

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

    // TODO: Generate Mac only during class instantiation
    private fun generateHmac(algorithm: HOTP.HashAlgorithm, secret: ByteArray, data: ByteArray): ByteArray {
        val algName = "Hmac$algorithm"

        with (Mac.getInstance(algName)) {
            init(SecretKeySpec(secret, algName))
            return doFinal(data)
        }
    }

    private fun format(otp: Int, digits: Int): String {
        val otpStr = otp.toString()
        return otpStr.takeLast(digits).padStart(digits, '0')
    }

    // Overridden because of HOTPToken data class
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return (javaClass == other?.javaClass)
    }

    override fun hashCode(): Int = javaClass.hashCode()
}
