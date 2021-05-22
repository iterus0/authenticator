package xyz.iterus.authenticator.core.model.totp

import xyz.iterus.authenticator.core.model.hotp.HOTP

class Steam(private val rfc6238: RFC6238): TOTP {

    private val alphabet = arrayOf(
        '2', '3', '4', '5', '6', '7', '8', '9',
        'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'M',
        'N', 'P', 'Q', 'R', 'T', 'V', 'W', 'X', 'Y'
    )

    override fun generate(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): String =
        format(generateNumber(secret, time, period, digits, alg), digits)

    override fun generateNumber(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): Int =
        rfc6238.generateNumber(secret, time, period, digits, alg)


    private fun format(otp: Int, digits: Int): String {
        var otpNum = otp
        val base = alphabet.size
        val result = StringBuilder()

        for (idx in (0 until digits)) {
            result.append(alphabet[otpNum % base])
            otpNum /= base
        }

        return result.toString()
    }


    // Overridden because of TOTPToken data class
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Steam
        return (rfc6238 == other.rfc6238)
    }

    override fun hashCode(): Int = (31 * rfc6238.hashCode() + javaClass.hashCode())
}
