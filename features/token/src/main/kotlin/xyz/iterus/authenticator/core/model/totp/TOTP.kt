package xyz.iterus.authenticator.core.model.totp

import xyz.iterus.authenticator.core.model.hotp.HOTP

interface TOTP {

    fun generate(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): String

    fun generateNumber(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): Int
}
