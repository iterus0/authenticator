package xyz.iterus.authenticator.feature.token.domain.model.totp

import xyz.iterus.authenticator.feature.token.domain.model.hotp.HOTP

interface TOTP {

    fun generate(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): String

    fun generateNumber(secret: String, time: Long, period: Int, digits: Int, alg: HOTP.HashAlgorithm): Int
}
