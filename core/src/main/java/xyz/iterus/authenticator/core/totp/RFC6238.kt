package xyz.iterus.authenticator.core.totp

import xyz.iterus.authenticator.core.hotp.HOTP

class RFC6238(private val hotp: HOTP): TOTP {

    override fun generateOTP(
        secret: String,
        time: Long,
        period: Int,
        digits: Int,
        alg: HOTP.HashAlgorithm
    ): String = formatOTP(hotp.generateOTP(secret, time / period, digits, alg), digits)

    private fun formatOTP(otp: Int, digits: Int): String {
        val otp_str = otp.toString()
        val size_diff = digits - otp_str.length

        if (size_diff > 0) {
            return ("0".repeat(size_diff) + otp_str)
        } else {
            return otp_str.drop(size_diff)
        }
    }
}
