package xyz.iterus.authenticator.token.totp

import xyz.iterus.authenticator.token.hotp.RFC4226

interface TOTP {

    fun generate(secret: String, time: Long, period: Int, digits: Int, alg: RFC4226.HashAlgorithm): String

    fun generateNumber(secret: String, time: Long, period: Int, digits: Int, alg: RFC4226.HashAlgorithm): Int
}
