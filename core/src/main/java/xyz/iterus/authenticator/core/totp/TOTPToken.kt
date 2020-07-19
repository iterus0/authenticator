package xyz.iterus.authenticator.core.totp

import xyz.iterus.authenticator.core.token.Token
import xyz.iterus.authenticator.core.hotp.HOTP

class TOTPToken(
        override val label: String,
        override val imageUrl: String,
        private val secret: String,
        private val period: Int,
        private val digits: Int,
        private val alg: HOTP.HashAlgorithm,
        private val totp : TOTP
    ): Token {


    override fun getToken(): String {
        val currentTime = System.currentTimeMillis() / 1000
        return totp.generateOTP(secret, currentTime, period, digits, alg)
    }
}
