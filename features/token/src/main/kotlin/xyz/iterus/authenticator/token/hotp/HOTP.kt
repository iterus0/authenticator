package xyz.iterus.authenticator.token.hotp

interface HOTP {

    fun generate(secret: String, counter: Long): String
}
