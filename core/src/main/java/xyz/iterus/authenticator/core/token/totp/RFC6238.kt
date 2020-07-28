package xyz.iterus.authenticator.core.token.totp

import xyz.iterus.authenticator.core.token.hotp.HOTP

class RFC6238(private val hotp: HOTP): TOTP {

    override fun generateOTP(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): String =
        hotp.generateOTP(secret, time / period, digits, alg)

    override fun generateOTPNumber(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): Int =
        hotp.generateOTPNumber(secret, time / period, digits, alg)


    // Overridden because of TOTPToken data class
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }
        other as RFC6238
        return (hotp == other.hotp)
    }

    override fun hashCode(): Int = (31 * hotp.hashCode() + javaClass.hashCode())
}
