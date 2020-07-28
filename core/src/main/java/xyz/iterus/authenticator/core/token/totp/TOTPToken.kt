package xyz.iterus.authenticator.core.token.totp

import xyz.iterus.authenticator.core.token.Token
import xyz.iterus.authenticator.core.token.hotp.HOTP

data class TOTPToken(
    override val id: Int,
    override val label: String,
    override val imageUrl: String,
    private val totp : TOTP,
    private val secret: String,
    val period: Int = 30,
    val digits: Int = 6,
    val alg: HOTP.HashAlgorithm = HOTP.HashAlgorithm.SHA1
): Token {

    private val periodMs = period * 1000


    override fun getToken(): String {
        val currentTime = System.currentTimeMillis() / 1000
        return totp.generateOTP(secret, currentTime, period, digits, alg)
    }


    fun getRemainingTimeMs(): Long {
        val currentTime = System.currentTimeMillis()

        return (periodMs - currentTime % periodMs)
    }

    fun getRemainingTimeNormalized(): Double {
        return (getRemainingTimeMs().toDouble() / periodMs)
    }

    fun isNewToken(): Boolean {
        //TODO: Is not always correct with DiffUtil
        return (periodMs - getRemainingTimeMs()) < 1000
    }
}