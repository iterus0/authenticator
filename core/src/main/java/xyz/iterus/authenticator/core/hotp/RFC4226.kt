package xyz.iterus.authenticator.core.hotp

import xyz.iterus.authenticator.core.toByteArray
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.math.pow

class RFC4226: HOTP {

    override fun generateOTP(secret: String, counter: Long, digits: Int, algorithm: HOTP.HashAlgorithm): Int {

        val hmac = generateHmac(algorithm, secret.toByteArray(), counter.toByteArray())

        val offset = hmac.last().toInt() and 0xF
        val otp = hmac.slice(offset..offset+3)
            .map { it.toInt() }
            .mapIndexed { idx, it -> if (idx == 0) (it and 0x7F) else (it and 0xFF) }
            .asReversed().mapIndexed { idx, it -> it shl 8*idx }
            .reduce { acc, it -> acc or it }

        val div = (10.0).pow(digits).toInt()
        return otp % div
    }

    private fun generateHmac(algorithm: HOTP.HashAlgorithm, secret: ByteArray, data: ByteArray): ByteArray {
        val algName = "Hmac$algorithm"

        with (Mac.getInstance(algName)) {
            init(SecretKeySpec(secret, algName))
            return doFinal(data)
        }
    }
}
