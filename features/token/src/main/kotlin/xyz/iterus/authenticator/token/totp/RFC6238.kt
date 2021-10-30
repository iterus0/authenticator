package xyz.iterus.authenticator.token.totp

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import xyz.iterus.authenticator.token.hotp.HOTP
import xyz.iterus.authenticator.token.hotp.RFC4226

class RFC6238(
    private val hotp: HOTP = RFC4226(RFC4226.HashAlgorithm.SHA1)
) : TOTP {

    override fun generateToken(secret: String, time: Long, period: Int, digits: Int): String =
        hotp.generateToken(secret, time / period, digits)

    override fun observeToken(secret: String, period: Int, digits: Int): Flow<String> {
        return generateSequence { System.currentTimeMillis() / 1000 }.asFlow()
            .map { currentTime -> generateToken(secret, currentTime, period, digits) }
            .flowOn(Dispatchers.Default)
    }

    // Overridden because of TOTPToken data class
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RFC6238
        return (hotp == other.hotp)
    }

    override fun hashCode(): Int = (31 * hotp.hashCode() + javaClass.hashCode())
}
