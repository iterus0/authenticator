package xyz.iterus.authenticator.core.model.hotp

import xyz.iterus.authenticator.core.model.Token

data class HOTPToken(
    override val id: Int,
    override val label: String,
    override val imageUrl: String,
    private val hotp: HOTP,
    private val secret: String,
    var counter: Long = 1,
    val digits: Int = 6,
    val alg: HOTP.HashAlgorithm = HOTP.HashAlgorithm.SHA1
): Token {

    override fun getToken(): String {
        val token = hotp.generate(secret, counter, digits, alg)
        counter += 1

        return token
    }
}
