package xyz.iterus.authenticator.token.totp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Steam(private val rfc6238: RFC6238) : TOTP {

    private val alphabet = arrayOf(
        '2', '3', '4', '5', '6', '7', '8', '9',
        'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'M',
        'N', 'P', 'Q', 'R', 'T', 'V', 'W', 'X', 'Y'
    )
    private val radix = alphabet.size

    override fun generateToken(secret: String, time: Long, period: Int): String {
        val rfc6238Token = rfc6238.generateToken(secret, time, period)
        return format(rfc6238Token)
    }

    override fun observeToken(secret: String, period: Int): Flow<String> {
        return rfc6238.observeToken(secret, period)
            .map { token -> format(token) }
    }

    /**
     * Steam token is basically RFC6238 token converted to custom number system
     */
    private fun format(otp: String, digits: Int = 5): String {
        val otpNum = otp.toIntOrNull() ?: return ""

        return otpNum.toString(radix).asSequence()
            .map { digit -> alphabet[digit.digitToInt(radix)] }
            .take(digits)
            .toString()
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
