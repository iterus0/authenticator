package xyz.iterus.authenticator.core.totp

import xyz.iterus.authenticator.core.hotp.HOTP

interface TOTP {

    fun generateOTP(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): String
}
