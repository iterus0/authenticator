package xyz.iterus.authenticator.core.token.totp

import xyz.iterus.authenticator.core.token.hotp.HOTP

interface TOTP {

    fun generateOTP(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): String

    fun generateOTPNumber(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): Int
}
