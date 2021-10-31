package xyz.iterus.authenticator.token.totp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class Steam(
    private val rfc6238: RFC6238 = RFC6238()
) : TOTP {

    private val alphabet = arrayOf(
        '2', '3', '4', '5', '6', '7', '8', '9',
        'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'M',
        'N', 'P', 'Q', 'R', 'T', 'V', 'W', 'X', 'Y'
    )
    private val radix = alphabet.size

    override fun generateToken(secret: String, time: Long, period: Int, digits: Int): String {
        val rfc6238Token = rfc6238.generateToken(secret, time, period, digits)
        return format(rfc6238Token)
    }

    override fun observeToken(secret: String, period: Int, digits: Int): Flow<String> {
        return rfc6238.observeToken(secret, period, digits)
            .map { token -> format(token, digits) }
            .flowOn(Dispatchers.Default)
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
}
