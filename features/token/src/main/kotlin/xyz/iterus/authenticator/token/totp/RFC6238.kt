package xyz.iterus.authenticator.token.totp

import xyz.iterus.authenticator.token.hotp.HOTP
import xyz.iterus.authenticator.token.hotp.RFC4226

class RFC6238(private val hotp: HOTP) : TOTP {

    override fun generate(secret: String, time: Long, period: Int, digits: Int, alg: RFC4226.HashAlgorithm): String =
        hotp.generate(secret, time / period, digits, alg)

    override fun generateNumber(secret: String, time: Long, period: Int, digits: Int, alg: RFC4226.HashAlgorithm): Int =
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
