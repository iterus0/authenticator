package xyz.iterus.authenticator.token.totp

import xyz.iterus.authenticator.token.hotp.HOTP

interface TOTP {

    fun generate(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): String

    fun generateNumber(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): Int
}
