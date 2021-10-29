package xyz.iterus.authenticator.core.model.totp

import xyz.iterus.authenticator.core.model.hotp.HOTP

class RFC6238(private val hotp: HOTP): TOTP {

    override fun generate(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): String =
        hotp.generate(secret, time / period, digits, alg)

    override fun generateNumber(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): Int =
        hotp.generateNumber(secret, time / period, digits, alg)


    // Overridden because of TOTPToken data class
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RFC6238
        return (hotp == other.hotp)
    }

    override fun hashCode(): Int = (31 * hotp.hashCode() + javaClass.hashCode())
}
