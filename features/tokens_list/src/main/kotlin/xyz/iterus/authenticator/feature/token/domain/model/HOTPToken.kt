package xyz.iterus.authenticator.feature.token.domain.model

import xyz.iterus.authenticator.token.hotp.HOTP
import xyz.iterus.authenticator.token.hotp.RFC4226

data class HOTPToken(
    override val id: Int,
    override val label: String,
    override val imageUrl: String,
    private val hotp: HOTP,
    private val secret: String,
    var counter: Long = 1,
    val digits: Int = 6,
    val alg: RFC4226.HashAlgorithm = RFC4226.HashAlgorithm.SHA1
): Token {

    override fun getToken(): String {
        val token = hotp.generate(secret, counter)
        counter += 1

        return token
    }
}
