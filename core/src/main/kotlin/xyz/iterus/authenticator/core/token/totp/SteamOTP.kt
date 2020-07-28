package xyz.iterus.authenticator.core.token.totp

import xyz.iterus.authenticator.core.token.hotp.HOTP

class SteamOTP(private val rfc6238: RFC6238): TOTP {

    private val steamChars = arrayOf(
        '2', '3', '4', '5', '6', '7', '8', '9', 'B', 'C',
        'D', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q',
        'R', 'T', 'V', 'W', 'X', 'Y'
    )

    override fun generateOTP(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): String =
        formatOTP(generateOTPNumber(secret, time, period, digits, alg), digits)

    override fun generateOTPNumber(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): Int =
        rfc6238.generateOTPNumber(secret, time, period, digits, alg)


    private fun formatOTP(otp: Int, digits: Int): String {
        var otpNum = otp
        val res = StringBuilder()
        val base = steamChars.size

        for (i in (0 until digits)) {
            res.append(steamChars[otpNum % base])
            otpNum /= base
        }

        return res.toString()
    }


    // Overridden because of TOTPToken data class
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }
        other as SteamOTP
        return (rfc6238 == other.rfc6238)
    }

    override fun hashCode(): Int = (31 * rfc6238.hashCode() + javaClass.hashCode())
}
