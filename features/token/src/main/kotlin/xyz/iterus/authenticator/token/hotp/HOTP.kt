package xyz.iterus.authenticator.token.hotp

interface HOTP {

    fun generate(secret: String, counter: Long, digits: Int, algorithm: RFC4226.HashAlgorithm): String

    fun generateNumber(secret: String, counter: Long, digits: Int, algorithm: RFC4226.HashAlgorithm): Int
}
